package com.searchapp.test;

import com.searchapp.dto.SearchDto;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest extends AbstractTestNGSpringContextTests {


    @Autowired
    RestTemplate template;

    @Autowired
    HttpSolrClient httpSolrClient;

    private final Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

    @Test
    public void testInternalSearchMiss() {
//        String query = "happy dog";
        String query = "lion king";

        SearchDto response = template.getForObject("http://localhost:8080/search/"+ query, SearchDto.class);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(response.getData().size(),  5, "No of records should be 5");
    }

    @Test
    public void testInternalSearch() {
        String title = "This is sample title";
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

        SearchDto response = template.getForObject("http://localhost:8080/search/"+ title, SearchDto.class);

        logger.debug("response from API:{}", response);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertTrue(response.getData().size() > 0);
        Assert.assertEquals(response.getData().size(), 5, "No of records should be 5");

        Assert.assertTrue(response.getData().get(0).getGif_id().contains(id));
        Assert.assertTrue(response.getData().get(0).getUrl().contains(url));
    }
}
