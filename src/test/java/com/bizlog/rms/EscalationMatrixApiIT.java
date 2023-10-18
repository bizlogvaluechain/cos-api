package com.bizlog.rms;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.escalationMatrix.EscalationMatrix;
import com.bizlog.rms.repository.EscalationMatrixRepository;
import com.bizlog.rms.utils.DataLoaderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EscalationMatrixApiIT extends BaseApiTest {

    @Autowired
    private EscalationMatrixRepository escalationMatrixRepository;

    @BeforeEach
    void beforeEach() {
        Client client = DataLoaderUtil.getClient();
        client = clientRepository.save(client);
        DataLoaderUtil.getEscalationMatrix(client).forEach(escalationMatrixRepository::save);
    }

    @AfterEach
    void afterEach() {
        escalationMatrixRepository.deleteAll();
        clientRepository.deleteAll();
    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        int clientId = 1;
        int id = 1;
        this.mockMvc.perform(get("/api/v1/{clientId}/escalation-matrix/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/{clientId}/escalation-matrix/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_escalationMatrix() throws Exception {
        int clientId = 1;
        EscalationMatrix escalationMatrix = new EscalationMatrix();
        escalationMatrix.setAccountContactInfo("IDP");
        escalationMatrix.setItContactInfo("IDP");
        escalationMatrix.setBusinessContactInfo("IDP");
        escalationMatrix.setOpsContactInfo("IDP");
        escalationMatrix.setEmergencyContactInfo("IDP");
        this.mockMvc
                .perform(post("/api/v1/{clientId}/escalation-matrix", clientId).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(escalationMatrix)))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_not_create_new_escalationMatrix() throws Exception {
        int clientId = 11;
        EscalationMatrix escalationMatrix = new EscalationMatrix();
        escalationMatrix.setAccountContactInfo("IDP");
        escalationMatrix.setItContactInfo("IDP");
        escalationMatrix.setBusinessContactInfo("IDP");
        escalationMatrix.setOpsContactInfo("IDP");
        escalationMatrix.setEmergencyContactInfo("IDP");
        this.mockMvc
                .perform(post("/api/v1/{clientId}/escalation-matrix", clientId).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(escalationMatrix)))
                .andDo(print()).andExpect(status().isNotFound());
    }
}
