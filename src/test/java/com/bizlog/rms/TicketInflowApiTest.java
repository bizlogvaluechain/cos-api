package com.bizlog.rms;

import com.bizlog.rms.dto.SOP_TAT.TicketInflowDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow;
import com.bizlog.rms.repository.TicketInflowRepository;
import com.bizlog.rms.utils.DataLoaderUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class TicketInflowApiTest extends BaseApiTest {

    @Autowired
    private TicketInflowRepository ticketCreationConfigRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getTicketCreationConfig(getOrganization()).forEach(ticketCreationConfigRepository::save);
    }

    @AfterEach
    void afterEach() {
        ticketCreationConfigRepository.deleteAll();
        organizationRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Long clientId = ticketCreationConfigRepository.findAll().get(0).getclientId();
        Long id = ticketCreationConfigRepository.findAll().get(0).getId();
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/ticket_info/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/ticket_info/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_ticket_creation_config() throws Exception {
        Organization organization = getOrganization();
        List<String> creationThroughList = Arrays.asList("Api", "Lr", "excel");
        TicketInflow ticketCreationConfig = new com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow();
        ticketCreationConfig.setId(1L);
        ticketCreationConfig.setOrganization(organization);
        ticketCreationConfig.setTicketCreationThrough(creationThroughList);
        ticketCreationConfig.setTicketType(Arrays.asList("Api", "Lr", "excel"));
        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/ticket_info", organization.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(ticketCreationConfig).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_not_create_new_ticket_in_flow() throws Exception {
        int clientId = 111;
        List<String> creationThroughList = Arrays.asList("Api", "Lr", "excel");
        TicketInflow ticketCreationConfig = new com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow();
        ticketCreationConfig.setTicketCreationThrough(creationThroughList);

        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/ticket_info", clientId).contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(ticketCreationConfig).orElse("")))
                .andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    void should_update_existing_ticket_in_flow() throws Exception {

        TicketInflow existingTicketCreationConfig = new com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow();

        existingTicketCreationConfig.setTicketType(Arrays.asList("Api", "Lr", "excel"));
        existingTicketCreationConfig.setTicketCreationThrough(Arrays.asList("Api", "Lr", "excel"));
        Organization organization = getOrganization();
        existingTicketCreationConfig.setOrganization(organization);
        existingTicketCreationConfig = ticketCreationConfigRepository.save(existingTicketCreationConfig);

        TicketInflowDTO updatedTicketCreationConfig = getMapper().toDTO(existingTicketCreationConfig);
        updatedTicketCreationConfig.setTicketCreationThrough(Arrays.asList("Api", "pr", "excelBizlog"));

        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/ticket_info/{id}", organization.getId(),
                        existingTicketCreationConfig.getId()).contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(updatedTicketCreationConfig).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().json(toJson(updatedTicketCreationConfig).orElse("")));
    }

    @Test
    void should_not_update_existing_ticket_in_flow() throws Exception {
        Long clientId = 99L;
        Long id = 999L;

        TicketInflow updatedTicketCreationConfig = new com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow();
        updatedTicketCreationConfig.setTicketCreationThrough(Arrays.asList("Api", "Lr", "excel"));
        this.mockMvc.perform(put("/api/v1/cos/{clientId}/ticket_info/{id}", clientId, id)
                .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedTicketCreationConfig).orElse("")))
                .andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    void should_delete_existing_ticket_in_flow() throws Exception {

        TicketInflow existingTicketCreationConfig = new com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow();
        existingTicketCreationConfig.setTicketType(Arrays.asList("Api", "Lr", "excel"));
        existingTicketCreationConfig.setTicketCreationThrough(Arrays.asList("Api", "Lr", "excel"));
        Organization organization = getOrganization();
        existingTicketCreationConfig.setOrganization(organization);
        existingTicketCreationConfig = ticketCreationConfigRepository.save(existingTicketCreationConfig);

        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/ticket_info/{id}", organization.getId(),
                existingTicketCreationConfig.getId())).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void should_not_delete_nonexistent_ticket_in_flow() throws Exception {
        int clientId = 11;
        int nonexistentId = 999;
        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/ticket_info/{id}", clientId, nonexistentId)).andDo(print())
                .andExpect(status().isNotFound());
    }
}
