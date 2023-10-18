package com.bizlog.rms;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.utils.DataLoaderUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerApiIT extends BaseApiTest{

    public static final String CLIENT_URL = "/api/v1/client";
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        DataLoaderUtil.getClients().forEach(clientRepository::save);
    }

    @AfterEach
    void afterEach() {
        clientRepository.deleteAll();
    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Client client = clientRepository.findById(1L).orElseThrow();
        String expected = toJson(client).orElse("");
        this.mockMvc.perform(get(CLIENT_URL+"/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        this.mockMvc.perform(get(CLIENT_URL+"/{id}", 11))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
