package com.bizlog.rms.service;

import com.bizlog.rms.dto.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class  KafkaService {

    private static final String TOPIC = "bizlog_cos_data";
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(ClientDTO clientDTO) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(clientDTO);
            kafkaTemplate.send(TOPIC, jsonMessage);
        } catch (Exception e) {
            log.error("Error occurred while sending message to Kafka", e);
        }
    }


}
