package com.searchapp.test.service;

import com.searchapp.dto.Gif;
import com.searchapp.service.InternalSearchImpl;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InternalSearchImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    InternalSearchImpl internalSearch;

    @Autowired
    HttpSolrClient httpSolrClient;

    private final Logger logger = LoggerFactory.getLogger(InternalSearchImpl.class);

    @Test
    public void testSolrReadNull() {
        List<Gif> resp = internalSearch.search("sample query");
        Assert.assertNull(resp);
    }

    @Test
    public void testSolrReadNotNull() {

        String title = "funny ";
        String id = "123";
        String url = "http://123.com/123";

        SolrInputDocument solrDoc1 = new SolrInputDocument();
        solrDoc1.addField("title", title + "1");
        solrDoc1.addField("id", id + "1");
        solrDoc1.addField("url", url + "1");

        SolrInputDocument solrDoc2 = new SolrInputDocument();
        solrDoc2.addField("title", title + "2");
        solrDoc2.addField("id", id + "2");
        solrDoc2.addField("url", url + "2");

        SolrInputDocument solrDoc3 = new SolrInputDocument();
        solrDoc3.addField("title", title + "3");
        solrDoc3.addField("id", id + "3");
        solrDoc3.addField("url", url + "3");

        SolrInputDocument solrDoc4 = new SolrInputDocument();
        solrDoc4.addField("title", title + "4");
        solrDoc4.addField("id", id + "4");
        solrDoc4.addField("url", url + "4");

        SolrInputDocument solrDoc5 = new SolrInputDocument();
        solrDoc5.addField("title", title + "5");
        solrDoc5.addField("id", id + "5");
        solrDoc5.addField("url", url + "5");



        try {
            httpSolrClient.add(solrDoc1);
            httpSolrClient.add(solrDoc2);
            httpSolrClient.add(solrDoc3);
            httpSolrClient.add(solrDoc4);
            httpSolrClient.add(solrDoc5);

            httpSolrClient.commit();
        } catch (SolrServerException | IOException e) {
            logger.error("error pushing doc to solr", e);
        }

        List<Gif> result = null;
        try {
            result = internalSearch.search(URLEncoder.encode(title, Charset.defaultCharset().name()));
        } catch (UnsupportedEncodingException e) {
            logger.error("error encoding search string", e);
        }
        logger.info("result from internal search:{}", result);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 5);
        Assert.assertTrue(result.get(0).getGif_id().contains(id));


    }
}
