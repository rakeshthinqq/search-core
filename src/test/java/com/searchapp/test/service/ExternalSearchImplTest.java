package com.searchapp.test.service;

import com.searchapp.dto.GiphyResponse;
import com.searchapp.service.ExternalSearchImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class ExternalSearchImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ExternalSearchImpl externalSearch;

    @Test
    public void giphySearchTest() {
        GiphyResponse resp = externalSearch.doGiphySearch("smiling dog");
        Assert.assertNotNull(resp);
        Assert.assertNotNull(resp.getData());
        Assert.assertTrue(resp.getData().size() > 0);
    }
}
