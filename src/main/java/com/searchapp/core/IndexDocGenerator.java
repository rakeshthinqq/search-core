package com.searchapp.core;

import com.searchapp.dto.GiphyDto;
import com.searchapp.dto.GiphyResponse;
import com.searchapp.util.DataTransferUtil;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Search doc consumer,
 * read from kafka and create solr docs
 */
@Component
public class IndexDocGenerator {


    private final HttpSolrClient httpSolrClient;

    private final  Logger logger = LoggerFactory.getLogger(IndexDocGenerator.class);

    private static final String TOPIC="giphy_payload";
    private static final String GROUPID="indexing_consumer";

    @Autowired
    public IndexDocGenerator(HttpSolrClient httpSolrClient) {
        this.httpSolrClient = httpSolrClient;
    }


    @KafkaListener(topics = TOPIC, groupId = GROUPID)
    public void consume(String message) throws IOException {
        logger.info("received message from topic:{}",TOPIC);


        GiphyResponse giphyResponse = DataTransferUtil.toGiphyResponse(message, GiphyResponse.class);
        for(GiphyDto giphyDto: giphyResponse.getData()) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("title", giphyDto.getTitle());
            doc.addField("id", giphyDto.getId());
            doc.addField("url", giphyDto.getUrl());
            try {
                httpSolrClient.add(doc);
            } catch (SolrServerException e) {
                logger.error("Indexing error", e);
            }

        }

        try {
            httpSolrClient.commit();
            httpSolrClient.close();
        } catch (SolrServerException e) {
            logger.error("Indexing error, commit failed ", e);
        }

    }

}
