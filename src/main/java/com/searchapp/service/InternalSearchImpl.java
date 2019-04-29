package com.searchapp.service;

import com.searchapp.dto.Gif;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Internal solr search impl
 */
@Component
public class InternalSearchImpl implements GifSearch{

    private final HttpSolrClient solrClient;

    private final Logger logger = LoggerFactory.getLogger(InternalSearchImpl.class);

    @Autowired
    public InternalSearchImpl(HttpSolrClient solrClient) {
        this.solrClient = solrClient;
    }

    @Override
    public List<Gif> search(String query) {
        logger.info("start internal search query:{}", query);

        final Map<String, String> queryParamMap = new HashMap<>();
        queryParamMap.put("q", "title:"+query);
        queryParamMap.put("fl", "id, url");
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);
        List<Gif> gifs = null;

        try {
            QueryResponse response = solrClient.query(queryParams);
            SolrDocumentList documents = response.getResults();
            if(documents !=null && !documents.isEmpty()) {
                gifs = new ArrayList<>();
                for (SolrDocument doc : documents) {
                    Object id = doc.get("id");
                    Object url = doc.get("url");
                    if (!StringUtils.isEmpty(id) && !StringUtils.isEmpty(url)) {
                        gifs.add(new Gif(id.toString(), url.toString()));
                    }
                }
            }

            if(documents != null) {
                logger.info("got response from solr:{}", documents.size());
            }

            return gifs;

        } catch (SolrServerException e) {
            logger.error("Error during solr read", e);
        } catch (IOException e) {
            logger.error("Error  during solr read", e);
        }
        return null;
    }

}
