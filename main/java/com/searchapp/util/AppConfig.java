package com.searchapp.util;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Value("${giphy.api.connection-timeout}")
    private String conTimeOut;

    @Value("${giphy.api.read-timeout}")
    private String readTimeOut;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return  builder
                .setConnectTimeout(Duration.ofMillis(Long.valueOf(conTimeOut)))
                .setReadTimeout(Duration.ofMillis(Long.valueOf((readTimeOut)))).build();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
