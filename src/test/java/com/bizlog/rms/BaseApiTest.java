package com.bizlog.rms;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.utils.DataLoaderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@Data
public class BaseApiTest {

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    GenericMapper mapper;

    private Client client = null;

    void beforeEach() {
        client = DataLoaderUtil.getClient();
        client = clientRepository.save(client);
    }

    public static Optional<String> toJson(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule simpleModule = new SimpleModule();
            objectMapper.registerModule(simpleModule);
            String jsonInString = objectMapper.writeValueAsString(obj);
            return Optional.of(jsonInString);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
