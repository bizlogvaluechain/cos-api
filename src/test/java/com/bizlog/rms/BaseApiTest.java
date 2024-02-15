package com.bizlog.rms;

import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.OrganizationRepository;
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
    public OrganizationRepository organizationRepository;

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    GenericMapper mapper;

    private Organization organization = null;

    void beforeEach() {
        organization = DataLoaderUtil.getClient();
        organization = organizationRepository.save(organization);
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
