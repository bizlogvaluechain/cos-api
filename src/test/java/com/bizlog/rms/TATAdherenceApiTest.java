package com.bizlog.rms;

import com.bizlog.rms.dto.SOP_TAT.TATAdherenceDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.sop.TATAdherence;
import com.bizlog.rms.repository.TATAdherenceRepository;
import com.bizlog.rms.utils.DataLoaderUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class TATAdherenceApiTest extends BaseApiTest {
    @Autowired
    private TATAdherenceRepository tatAdherenceRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getTATActivity(getClient()).forEach(tatAdherenceRepository::save);
    }

    @AfterEach
    void afterEach() {
        tatAdherenceRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Long clientId = tatAdherenceRepository.findAll().get(0).getclientId();
        Long id = tatAdherenceRepository.findAll().get(0).getId();
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/tat_adherence/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/tat_adherence/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_tatAdherence() throws Exception {
        Client client = getClient();

        TATAdherence tatActivity = new TATAdherence();
        tatActivity.setTatAdherenceRequired(true);
        tatActivity.setBizlog("yes");
        tatActivity.setCustomer("yes");

        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/tat_adherence", client.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(tatActivity).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }
    @Test
    void should_not_create_new_tatAdherence() throws Exception {
        Long clientId = 55L;

        TATAdherence tatActivity = new TATAdherence();
        tatActivity.setTatAdherenceRequired(true);
        tatActivity.setBizlog("yes");
        tatActivity.setCustomer("yes");

        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/tat_adherence", clientId)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(tatActivity).orElse("")))
                .andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    void should_update_existing_tatAdherence() throws Exception {

        TATAdherence initialTatActivity = new TATAdherence();
        initialTatActivity.setTatAdherenceRequired(true);

        Client client = getClient();
        initialTatActivity.setClient(client);
        initialTatActivity.setTatAdherenceRequired(true);
        initialTatActivity.setCustomer("yes");
        initialTatActivity = tatAdherenceRepository.save(initialTatActivity);

        TATAdherenceDTO updatedTatActivity = getMapper().toDTO(initialTatActivity);
        updatedTatActivity.setTatAdherenceRequired(false);
        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/tat_adherence/{id}", client.getId(), initialTatActivity.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedTatActivity).orElse("")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(toJson(updatedTatActivity).orElse("")));
    }

    @Test
    void should_not_update_existing_tatAdherence() throws Exception {
        int clientId = 12;
        int id = 21;
        TATAdherence updatedTatActivity = new TATAdherence();
        updatedTatActivity.setTatAdherenceRequired(false);
        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/tat_adherence/{id}", clientId, id)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedTatActivity).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_delete_existing_tatActivity() throws Exception {

        TATAdherence tatActivity = new TATAdherence();
        tatActivity.setTatAdherenceRequired(false);
        tatActivity.setCustomer("yes");
        Client client = getClient();
        tatActivity.setClient(client);
        tatActivity = tatAdherenceRepository.save(tatActivity);

        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/tat_adherence/{id}", client.getId(), tatActivity.getId()))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void should_not_delete_nonexistent_tatActivity() throws Exception {
        int clientId = 11;
        int nonexistentId = 999;
        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/tat_adherence/{id}", clientId, nonexistentId))
                .andDo(print()).andExpect(status().isNotFound());
    }
}
