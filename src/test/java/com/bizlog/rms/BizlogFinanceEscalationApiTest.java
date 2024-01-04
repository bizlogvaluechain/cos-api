package com.bizlog.rms;

import com.bizlog.rms.dto.escalationMatrix.EscalationMatrixDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.escalationMatrix.BizlogFinanceEscalation;
import com.bizlog.rms.repository.BizlogFinanceEscalationRepository;
import com.bizlog.rms.utils.DataLoaderUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class BizlogFinanceEscalationApiTest extends BaseApiTest {

    @Autowired
    private BizlogFinanceEscalationRepository bizlogFinanceEscalationRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getEscalationMatrix(getClient()).forEach(bizlogFinanceEscalationRepository::save);
    }

    @AfterEach
    void afterEach() {
        bizlogFinanceEscalationRepository.deleteAll();
        clientRepository.deleteAll();
    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Long clientId = bizlogFinanceEscalationRepository.findAll().get(0).getclientId();
        Long id = bizlogFinanceEscalationRepository.findAll().get(0).getId();
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/bizlog-finance-escalation/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/bizlog-finance-escalation/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_escalationMatrix() throws Exception {
        Client client = getClient();
        List<BizlogFinanceEscalation> escalationMatrixList = new ArrayList<>();
        BizlogFinanceEscalation escalationMatrix = new BizlogFinanceEscalation();
        escalationMatrix.setDesignation("IDP");
        escalationMatrix.setFirstName("IDP");
        escalationMatrix.setLastName("IDP");
        escalationMatrix.setEmailAddress("IDP");
        escalationMatrix.setMobile("IDP");
        escalationMatrixList.add(escalationMatrix);
        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/bizlog-finance-escalation", client.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(escalationMatrixList).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_not_create_new_escalationMatrix() throws Exception {
        int clientId = 11;
        List<BizlogFinanceEscalation> escalationMatrixList = new ArrayList<>();
        BizlogFinanceEscalation escalationMatrix = new BizlogFinanceEscalation();
        escalationMatrix.setDesignation("IDP");
        escalationMatrix.setFirstName("IDP");
        escalationMatrix.setLastName("IDP");
        escalationMatrix.setEmailAddress("IDP");
        escalationMatrix.setMobile("IDP");
        escalationMatrixList.add(escalationMatrix);

        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/bizlog-finance-escalation", clientId)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(escalationMatrixList).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_update_existing_escalationMatrix() throws Exception {
        BizlogFinanceEscalation initialEscalationMatrix = new BizlogFinanceEscalation();
        initialEscalationMatrix.setLastName("InitialItInfo");
        initialEscalationMatrix.setMobile("InitialBusinessInfo");
        initialEscalationMatrix.setDesignation("InitialOpsInfo");
        initialEscalationMatrix.setFirstName("InitialEmergencyInfo");
        initialEscalationMatrix.setEmailAddress("initial@gmail.com");
        Client client = getClient();
        initialEscalationMatrix.setClient(client);
        initialEscalationMatrix = bizlogFinanceEscalationRepository.save(initialEscalationMatrix);

        EscalationMatrixDTO updatedEscalationMatrix = getMapper().toDTO(initialEscalationMatrix);
        updatedEscalationMatrix.setLastName("UpdatedItInfo");
        updatedEscalationMatrix.setMobile("UpdatedBusinessInfo");
        updatedEscalationMatrix.setDesignation("UpdatedOpsInfo");

        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/bizlog-finance-escalation/{id}", client.getId(),
                        initialEscalationMatrix.getId()).contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(updatedEscalationMatrix).orElse("")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(toJson(updatedEscalationMatrix).orElse("")));
    }

    @Test
    void should_not_update_existent_escalationMatrix() throws Exception {

        int clientId = 11;
        int nonexistentEscalationMatrixId = 999;

        BizlogFinanceEscalation updatedEscalationMatrix = new BizlogFinanceEscalation();
        updatedEscalationMatrix.setLastName("UpdatedItInfo");
        updatedEscalationMatrix.setMobile("UpdatedBusinessInfo");
        updatedEscalationMatrix.setDesignation("UpdatedOpsInfo");

        // Perform the PUT request to update the EscalationMatrix
        this.mockMvc.perform(
                put("/api/v1/cos/{clientId}/bizlog-finance-escalation/{id}", clientId, nonexistentEscalationMatrixId)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedEscalationMatrix).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_delete_existing_escalationMatrix() throws Exception {
        BizlogFinanceEscalation escalationMatrix = new BizlogFinanceEscalation();
        escalationMatrix.setDesignation("IDP");
        escalationMatrix.setFirstName("IDP");
        escalationMatrix.setLastName("IDP");
        escalationMatrix.setEmailAddress("IDP");
        escalationMatrix.setMobile("IDP");
        Client client = getClient();
        escalationMatrix.setClient(client);
        escalationMatrix = bizlogFinanceEscalationRepository.save(escalationMatrix);

        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/bizlog-finance-escalation/{id}", client.getId(),
                escalationMatrix.getId())).andDo(print()).andExpect(status().isNoContent());

    }

}
