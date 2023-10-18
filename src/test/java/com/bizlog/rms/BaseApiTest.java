package com.bizlog.rms;

import com.bizlog.rms.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class BaseApiTest {

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public MockMvc mockMvc;

    public static Optional<String> toJson(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule simpleModule = new SimpleModule();
            objectMapper.registerModule(simpleModule);
            String jsonInString = objectMapper.writeValueAsString(obj);
            return Optional.of(jsonInString);
        } catch(Exception e) {
            return Optional.empty();
        }
    }
}
