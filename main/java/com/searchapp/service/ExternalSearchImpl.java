package com.searchapp.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.searchapp.core.SearchException;
import com.searchapp.dto.GiphyResponse;
import com.searchapp.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

@EnableHystrix
@Component
public class ExternalSearchImpl {

    private final RestTemplate restTemplate;

    @Value("${giphy.api.url}")
    private String url;

    @Value("${giphy.api.key}")
    private String apiKey;

    @Value(("${giphy.api.record-count}"))
    private String nofRecords;

    private final Logger logger = LoggerFactory.getLogger(ExternalSearchImpl.class);

    private final AtomicLong errorCounter = new AtomicLong();

    @Autowired
    public ExternalSearchImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @HystrixCommand(defaultFallback = "fallBack", fallbackMethod = "fallBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")}
    )
    public GiphyResponse doGiphySearch(String query) {
        String giphyUrl = String.format(url, apiKey, query, nofRecords);

        GiphyResponse giphyResponse = restTemplate.getForObject(giphyUrl, GiphyResponse.class);
        logger.info("Giphy response received");
        logger.debug("Giphy api result:{}", giphyResponse);
        return giphyResponse;
    }


    @SuppressWarnings("unused")
    public GiphyResponse fallBack(String query) {
        throw new SearchException(HttpStatus.BAD_GATEWAY, String.format(Constants.GIPHY_SVC_ERROR, query, errorCounter.addAndGet(1)));
    }
}
