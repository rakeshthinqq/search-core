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

    @Value("${kafka.giphy_payload}")
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
        logger.debug("publishing message to kafka for indexing");
        producer.sendMessage(DataTransferUtil.convertGiphyResponse(giphyResponse), giphyPayloadTopic);
        logger.info("published message to kafka");
    }
}
