package com.bizlog.rms;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationBasedOn;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationConfig;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationThrough;
import com.bizlog.rms.repository.TicketCreationConfigRepository;
import com.bizlog.rms.utils.DataLoaderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TicketCreationConfigApiIT extends BaseApiTest {

    @Autowired
    private TicketCreationConfigRepository ticketCreationConfigRepository;

    @BeforeEach
    void beforeEach() {
        Client client = DataLoaderUtil.getClient();
        client = clientRepository.save(client);
        DataLoaderUtil.getTicketCreationConfig(client).forEach(ticketCreationConfigRepository::save);
    }

    @AfterEach
    void afterEach() {
        ticketCreationConfigRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        int clientId = 1;
        int id = 1;
        this.mockMvc.perform(get("/api/v1/{clientId}/ticket-creation-config/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/{clientId}/ticket-creation-config/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }
    // @Test
    // void should_create_new_productInformation() throws Exception {
    // int clientId = 1;
    // TicketCreationThrough ticketCreationThrough = new TicketCreationThrough();
    // ticketCreationThrough.setApi("API");
    // ticketCreationThrough.setExcel("Excel");
    // ticketCreationThrough.setForm("form");
    //
    // TicketCreationBasedOn ticketCreationBasedOn = new TicketCreationBasedOn();
    // ticketCreationBasedOn.setAwbNumber("AWB");
    // ticketCreationBasedOn.setInvoiceNumber("invoice");
    // ticketCreationBasedOn.setComplaintNumber("complaint");
    // ticketCreationBasedOn.setOrderNumber("order");
    //
    // TicketCreationConfig ticketCreationConfig = new TicketCreationConfig();
    //
    // List<TicketCreationThrough> ticketCreationThroughs = new ArrayList<>();
    // ticketCreationThroughs.add(ticketCreationThrough);
    // ticketCreationConfig.setTicketCreationThrough(ticketCreationThroughs);
    // List<TicketCreationBasedOn> ticketCreationBasedOns = new ArrayList<>();
    // ticketCreationBasedOns.add(ticketCreationBasedOn);
    // ticketCreationConfig.setTicketCreationBasedOn(ticketCreationBasedOns);
    //
    // this.mockMvc
    // .perform(post("/api/v1/{clientId}/ticket-creation-config", clientId).contentType(MediaType.APPLICATION_JSON)
    // .content(new ObjectMapper().writeValueAsString(ticketCreationThrough)))
    // .andDo(print()).andExpect(status().is2xxSuccessful());
    // }

    @Test
    void should_not_create_new_productInformation() throws Exception {
        int clientId = 11;
        TicketCreationThrough ticketCreationThrough = new TicketCreationThrough();
        ticketCreationThrough.setApi("API");
        ticketCreationThrough.setExcel("Excel");
        ticketCreationThrough.setForm("form");

        TicketCreationBasedOn ticketCreationBasedOn = new TicketCreationBasedOn();
        ticketCreationBasedOn.setAwbNumber("AWB");
        ticketCreationBasedOn.setInvoiceNumber("invoice");
        ticketCreationBasedOn.setComplaintNumber("complaint");
        ticketCreationBasedOn.setOrderNumber("order");

        TicketCreationConfig ticketCreationConfig = new TicketCreationConfig();

        List<TicketCreationThrough> ticketCreationThroughs = new ArrayList<>();
        ticketCreationThroughs.add(ticketCreationThrough);
        ticketCreationConfig.setTicketCreationThrough(ticketCreationThroughs);
        List<TicketCreationBasedOn> ticketCreationBasedOns = new ArrayList<>();
        ticketCreationBasedOns.add(ticketCreationBasedOn);
        ticketCreationConfig.setTicketCreationBasedOn(ticketCreationBasedOns);

        this.mockMvc
                .perform(post("/api/v1/{clientId}/ticket-creation-config", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(ticketCreationThrough)))
                .andDo(print()).andExpect(status().isBadRequest());
    }
}