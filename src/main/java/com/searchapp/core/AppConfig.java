package com.searchapp.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Value("${giphy.api.conn-timeout}")
    private String url;

    @Value("${giphy.api.read-timeout}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        RestTemplate build = builder
                .setConnectTimeout(3000)
                .setReadTimeout(3000)
                .build();
        return build;
    }
}
