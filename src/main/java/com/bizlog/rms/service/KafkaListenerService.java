//package com.bizlog.rms.service;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class KafkaListenerService {
//
//    @KafkaListener(topics = "On-board", groupId = "your-group-id")
//    public void listenToOnBoard(String Json) {
//        System.out.println("Received message: {}" + Json);
//
//        String json = Json.substring(1, Json.length() - 1).replace("\\\"", "\"");
//        System.out.println("Received message: " + json);
//    }
//    @KafkaListener(topics = "Organization", groupId = "your-group-id")
//    public void listenToOrganization(String Json) {
//        System.out.println("Received message: {}" + Json);
//
//        String json = Json.substring(1, Json.length() - 1).replace("\\\"", "\"");
//        System.out.println("Received message: " + json);
//    }
//}