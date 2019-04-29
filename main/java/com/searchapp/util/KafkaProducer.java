package com.searchapp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> template;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public void sendMessage(String message, String topic) {
        logger.debug(String.format("#### -> Producing message -> %s", message));
        this.template.send(topic, message);
    }
}
