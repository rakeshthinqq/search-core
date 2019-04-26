package com.searchapp.service;

import com.searchapp.dto.Gif;
import com.searchapp.dto.GiphyDto;
import com.searchapp.dto.GiphyResponse;
import com.searchapp.util.GiphyDataTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GifSearchService implements GifSearch {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private RestTemplate restTemplate;

    @Value("${giphy.api.url}")
    private String url;

    @Value("${giphy.api.key}")
    private String apiKey;

    @Value(("${giphy.api.record-count}"))
    private String nofRecords;

    Logger logger = LoggerFactory.getLogger(GifSearchService.class);

    @Override
    public List<Gif> searchGifs(String query) {


        String giphyUrl = String.format(url, apiKey, query, nofRecords);

        logger.debug("search query:{}", giphyUrl);

        GiphyResponse giphyResponse = null;
        try {
            giphyResponse = restTemplate.getForObject(giphyUrl, GiphyResponse.class);
        } catch (Exception e){
            //TODO: throw run time exception
        }


        logger.debug("Giphy api result:{}", giphyResponse);

        if(giphyResponse == null) {
            return null;
        } else {
            List<GiphyDto> giphyDtos = giphyResponse.getData();
            return  GiphyDataTransfer.convertGiphyToGif(giphyDtos);
        }

    }
}
