package com.bizlog.rms.service;

import com.bizlog.rms.dto.ClientDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class  KafkaService {

    private static final String TOPIC = "bizlog_cos_data";
    @Autowired
    private final KafkaTemplate<String, ClientDTO> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, ClientDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(ClientDTO yourObject) {
        kafkaTemplate.send(TOPIC, yourObject);
    }


}
