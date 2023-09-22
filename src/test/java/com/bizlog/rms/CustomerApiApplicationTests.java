package com.bizlog.rms;

import com.bizlog.rms.repository.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CustomerApiApplicationTests {

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
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        this.mockMvc.perform(get("/api/v1/client/{id}", 0)).andDo(print()).andExpect(status().is4xxClientError());
    }

    // @Test
    // void should_retrieve_one_user() throws Exception {
    // this.mockMvc.perform(get("/api/v1/client/{id}", 0))
    // .andDo(print())
    // .andExpect(status().isOk())
    // .andExpect(content().contentType(APPLICATION_JSON))
    // .andExpect(jsonPath("$.id").value(1))
    // .andExpect(jsonPath("$.name").value("IDP"));
    // }

    //
    // @Test
    // void should_retrieve_all_users() throws Exception {
    // this.mockMvc.perform(get("/api/client"))
    // .andDo(print())
    // .andExpect(status().isOk())
    // .andExpect(content().contentType(APPLICATION_JSON))
    // .andExpect(jsonPath("$").isArray())
    // .andExpect(jsonPath("$", hasSize(5)))
    // .andExpect(jsonPath("$.[0].id").value(1))
    // .andExpect(jsonPath("$.[1].id").value(2))
    // .andExpect(jsonPath("$.[2].id").value(3))
    // .andExpect(jsonPath("$.[3].id").value(4))
    // .andExpect(jsonPath("$.[4].id").value(5));
    // }

}

