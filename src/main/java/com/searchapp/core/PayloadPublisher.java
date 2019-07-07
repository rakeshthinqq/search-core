package com.searchapp.core;

import com.searchapp.dto.GiphyResponse;
import com.searchapp.util.DataTransferUtil;
import com.searchapp.util.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class PayloadPublisher {

    @Value("${spring.kafka.giphy_topic}")
    String giphyPayloadTopic;

    private final KafkaProducer producer;

    private final Logger logger = LoggerFactory.getLogger(PayloadPublisher.class);

    @Autowired
    public PayloadPublisher(KafkaProducer producer) {
        this.producer = producer;
    }


    @Async
    @SuppressWarnings("unused")
    public void publish(GiphyResponse giphyResponse) {
        String pubMessage = DataTransferUtil.convertGiphyResponse(giphyResponse);
        logger.debug("publishing message to kafka for indexing- message:{}", pubMessage);

        producer.sendMessage(pubMessage, giphyPayloadTopic);
        logger.info("published message to kafka");
    }
}
