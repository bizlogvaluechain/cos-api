package com.bizlog.rms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService<T> {

    @Autowired
    private final KafkaTemplate<String, T> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, T message) {
        kafkaTemplate.send(topic, message);
    }
}
