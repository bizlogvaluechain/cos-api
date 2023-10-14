package com.bizlog.rms;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.repository.TATActivityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class TATActivityApiIT extends BaseApiTest {
    @Autowired
    private TATActivityRepository tatActivityRepository;

    @BeforeEach
    void beforeEach() {
        Client client = DataLoaderUtil.getClient();
        client = clientRepository.save(client);
        DataLoaderUtil.getTATActivity(client).forEach(tatActivityRepository::save);
    }

    @AfterEach
    void afterEach() {
        clientRepository.deleteAll();
        tatActivityRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        int clientId = 1;
        int id = 1;
        this.mockMvc.perform(get("/api/v1/{clientId}/sop/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/{clientId}/sop/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }
}
