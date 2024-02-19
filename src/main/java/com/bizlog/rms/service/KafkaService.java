package com.bizlog.rms.service;

import com.bizlog.rms.dto.OrganizationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {


    @Autowired
    private final KafkaTemplate<String, OrganizationDTO> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, OrganizationDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(String topic,OrganizationDTO yourObject) {
        kafkaTemplate.send(topic, yourObject);
    }
}
