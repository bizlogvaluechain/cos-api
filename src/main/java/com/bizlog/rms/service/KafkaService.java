package com.bizlog.rms.service;

import com.bizlog.rms.dto.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
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
            e.printStackTrace();
        }
    }

//    public void sendMessage(String message){
//
//        this.kafkaTemplate.send(TOPIC,message);
//    }

//    @Bean
//    public NewTopic createTopic(){
//
//        return new NewTopic(TOPIC,3,(short) 1);
//    }

}
