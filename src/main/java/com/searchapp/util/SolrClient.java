package com.searchapp.util;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrClient {


    @Value("${solr.url}")
    String solrUrl;

    @Value("${solr.connection-timeout}")
    String connectionTimeOut;

    @Value("${solr.socket-timeout}")
    String socketTimeOut;

    @Bean
    public HttpSolrClient httpSolrClient() {
        return new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(Integer.valueOf(connectionTimeOut))
                .withSocketTimeout(Integer.valueOf(socketTimeOut))
                .build();
    }

}
