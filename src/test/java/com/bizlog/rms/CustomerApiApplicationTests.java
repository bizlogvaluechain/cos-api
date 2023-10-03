package com.bizlog.rms;

import com.bizlog.rms.repository.ClientRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
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
        this.mockMvc.perform(get("/api/v1/client/{id}", 1))
                .andDo(print()).andExpect(status().isOk());
    }

}
