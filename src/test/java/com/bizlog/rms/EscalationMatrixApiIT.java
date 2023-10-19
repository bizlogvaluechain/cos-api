package com.bizlog.rms;

import com.bizlog.rms.dto.escalationMatrix.EscalationMatrixDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EscalationMatrixApiIT extends BaseApiTest {

    @Autowired
    private EscalationMatrixRepository escalationMatrixRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getEscalationMatrix(getClient()).forEach(escalationMatrixRepository::save);
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
        this.mockMvc.perform(post("/api/v1/{clientId}/escalation-matrix", clientId)
                .contentType(MediaType.APPLICATION_JSON).content(toJson(escalationMatrix).orElse(""))).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_update_existing_escalationMatrix() throws Exception {
        int clientId = 1;
        long id = 2;

        EscalationMatrix initialEscalationMatrix = new EscalationMatrix();
        initialEscalationMatrix.setAccountContactInfo("InitialAccountInfo");
        initialEscalationMatrix.setItContactInfo("InitialItInfo");
        initialEscalationMatrix.setBusinessContactInfo("InitialBusinessInfo");
        initialEscalationMatrix.setOpsContactInfo("InitialOpsInfo");
        initialEscalationMatrix.setEmergencyContactInfo("InitialEmergencyInfo");
        initialEscalationMatrix.setClient(getClient());
        initialEscalationMatrix = escalationMatrixRepository.save(initialEscalationMatrix);

        EscalationMatrixDTO updatedEscalationMatrix = getMapper().toDTO(initialEscalationMatrix);
        updatedEscalationMatrix.setAccountContactInfo("UpdatedAccountInfo");
        updatedEscalationMatrix.setItContactInfo("UpdatedItInfo");
        updatedEscalationMatrix.setBusinessContactInfo("UpdatedBusinessInfo");
        updatedEscalationMatrix.setOpsContactInfo("UpdatedOpsInfo");
        updatedEscalationMatrix.setEmergencyContactInfo("UpdatedEmergencyInfo");
        this.mockMvc
                .perform(put("/api/v1/{clientId}/escalation-matrix/{id}", clientId, id)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedEscalationMatrix).orElse("")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(toJson(updatedEscalationMatrix).orElse("")));

    }

    @Test
    void should_not_update_nonexistent_escalationMatrix() throws Exception {

        int clientId = 11;
        int nonexistentEscalationMatrixId = 999;

        EscalationMatrix updatedEscalationMatrix = new EscalationMatrix();
        updatedEscalationMatrix.setAccountContactInfo("UpdatedAccountInfo");
        updatedEscalationMatrix.setItContactInfo("UpdatedItInfo");
        updatedEscalationMatrix.setBusinessContactInfo("UpdatedBusinessInfo");
        updatedEscalationMatrix.setOpsContactInfo("UpdatedOpsInfo");
        updatedEscalationMatrix.setEmergencyContactInfo("UpdatedEmergencyInfo");

        // Perform the PUT request to update the EscalationMatrix
        this.mockMvc
                .perform(put("/api/v1/{clientId}/escalation-matrix/{id}", clientId, nonexistentEscalationMatrixId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(updatedEscalationMatrix).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_delete_existing_escalationMatrix() throws Exception {
        EscalationMatrix escalationMatrix = new EscalationMatrix();
        escalationMatrix.setAccountContactInfo("ToDeleteAccountInfo");
        escalationMatrix.setItContactInfo("ToDeleteItInfo");
        escalationMatrix.setBusinessContactInfo("ToDeleteBusinessInfo");
        escalationMatrix.setOpsContactInfo("ToDeleteOpsInfo");
        escalationMatrix.setEmergencyContactInfo("ToDeleteEmergencyInfo");
        Client client = getClient();
        escalationMatrix.setClient(client);
        escalationMatrix = escalationMatrixRepository.save(escalationMatrix);


        this.mockMvc.perform(delete("/api/v1/{clientId}/escalation-matrix/{id}",
                        client.getId(), escalationMatrix.getId())).andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    void should_not_delete_nonexistent_escalationMatrix() throws Exception {
        int clientId = 11;
        int nonexistentEscalationMatrixId = 999; // An ID that doesn't exist

        // Perform the DELETE request to delete a non-existent EscalationMatrix
        this.mockMvc
                .perform(delete("/api/v1/{clientId}/escalation-matrix/{id}", clientId, nonexistentEscalationMatrixId))
                .andDo(print()).andExpect(status().isNotFound());
    }

}
