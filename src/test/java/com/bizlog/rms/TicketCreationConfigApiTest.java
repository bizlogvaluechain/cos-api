package com.bizlog.rms;

import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationBasedOn;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationConfig;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationThrough;
import com.bizlog.rms.repository.TicketCreationConfigRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class TicketCreationConfigApiTest extends BaseApiTest {

    @Autowired
    private TicketCreationConfigRepository ticketCreationConfigRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getTicketCreationConfig(getClient()).forEach(ticketCreationConfigRepository::save);
    }

    @AfterEach
    void afterEach() {
        ticketCreationConfigRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Long clientId = ticketCreationConfigRepository.findAll().get(0).getclientId();
        Long id = ticketCreationConfigRepository.findAll().get(0).getId();
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/ticket-creation-config/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/ticket-creation-config/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }
    // @Test
    // void should_create_new_ticket_creation_config() throws Exception {
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
    // List<TicketCreationThrough> ticketCreationThroughList = new ArrayList<>();
    // ticketCreationThroughList.add(ticketCreationThrough);
    // ticketCreationConfig.setTicketCreationThrough(ticketCreationThroughs);
    // List<TicketCreationBasedOn> ticketCreationBasedOnList = new ArrayList<>();
    // ticketCreationBasedOnList.add(ticketCreationBasedOn);
    // ticketCreationConfig.setTicketCreationBasedOn(ticketCreationBasedOns);
    //
    // this.mockMvc
    // .perform(post("/api/v1/cos/{clientId}/ticket-creation-config", clientId).contentType(MediaType.APPLICATION_JSON)
    // .content(toJson(ticketCreationThrough).orElse("")))
    // .andDo(print()).andExpect(status().is2xxSuccessful());
    // }

    @Test
    void should_not_create_new_ticket_creation_config() throws Exception {
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
                .perform(post("/api/v1/cos/{clientId}/ticket-creation-config", clientId)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(ticketCreationThrough).orElse("")))
                .andDo(print()).andExpect(status().isBadRequest());
    }
}
