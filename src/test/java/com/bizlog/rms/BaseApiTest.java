package com.bizlog.rms;

import com.bizlog.rms.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class BaseApiTest {

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public MockMvc mockMvc;
}
