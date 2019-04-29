package com.searchapp.service;

import com.searchapp.core.PayloadPublisher;
import com.searchapp.dto.Gif;
import com.searchapp.dto.GiphyDto;
import com.searchapp.dto.GiphyResponse;
import com.searchapp.util.DataTransferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * core search impl
 * logic
 * 1) Do internal solr search
 * 2) call giphy search if internal search result < 5
 * 3) post giphy response in kafka for indexing - async post
 */
@Service
public class GifSearchServiceImpl implements GifSearch {


    private final PayloadPublisher publisher;

    @Autowired
    InternalSearchImpl internalSearch;

    @Autowired
    ExternalSearchImpl giphySearch;

    @Value(("${giphy.api.record-count}"))
    private String nofMinRecords;

    private final Logger logger = LoggerFactory.getLogger(GifSearchServiceImpl.class);

    @Autowired
    public GifSearchServiceImpl(PayloadPublisher publisher) {
        this.publisher = publisher;
    }


    @Override
    public List<Gif> search(String query) {

        logger.debug("search query:{}", query);
        int maxRecords = Integer.valueOf(nofMinRecords);

        List<Gif> result = internalSearch.search(query);
        if (CollectionUtils.isEmpty(result) || result.size() < maxRecords) {
            logger.info("internal search is not success, start - giphy search");
            GiphyResponse giphyResponse = giphySearch.doGiphySearch(query);

            if (giphyResponse == null || CollectionUtils.isEmpty(giphyResponse.getData())) {
                logger.info("Giphy response is null or empty");
            } else {
                List<GiphyDto> giphyDtos = giphyResponse.getData();
                if(giphyDtos.size() >= maxRecords) {
                    result = DataTransferUtil.convertGiphyToGif(giphyDtos);
                }

                //async publishing for indexing in internal solr
                publisher.publish(giphyResponse);
            }
        } else {
            result = result.subList(0, maxRecords);
        }

        return result;
    }



}
