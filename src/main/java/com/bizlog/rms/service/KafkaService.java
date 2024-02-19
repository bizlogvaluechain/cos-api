package com.bizlog.rms.service;


import com.bizlog.rms.entities.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {


    @Autowired
    private final KafkaTemplate<String, Organization> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, Organization> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(String topic,Organization yourObject) {
        kafkaTemplate.send(topic, yourObject);
    }
}
