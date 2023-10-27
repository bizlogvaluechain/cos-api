package com.bizlog.rms;

import com.bizlog.rms.dto.SOP_TAT.TATActivityDTO;
import com.bizlog.rms.dto.SOP_TAT.subLists.TATBreachDueTo;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.Specifications.TATActivity;
import com.bizlog.rms.repository.TATActivityRepository;
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
public class TATActivityApiTest extends BaseApiTest {
    @Autowired
    private TATActivityRepository tatActivityRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getTATActivity(getClient()).forEach(tatActivityRepository::save);
    }

    @AfterEach
    void afterEach() {
        tatActivityRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Long clientId = tatActivityRepository.findAll().get(0).getclientId();
        Long id = tatActivityRepository.findAll().get(0).getId();
        this.mockMvc.perform(get("/api/v1/{clientId}/tatActivities/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/{clientId}/tatActivities/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_tatActivity() throws Exception {
        Client client = getClient();
        TATBreachDueTo tatBreachDueTo = new TATBreachDueTo();
        tatBreachDueTo.setBizlog("abcdef");
        tatBreachDueTo.setCustomer("abcdef");
        tatBreachDueTo.setThirdPartyLogistics("abcdef");

        TATActivity tatActivity = new TATActivity();
        tatActivity.setTatForFirstMile("abcdef");
        tatActivity.setTatForLastMile("abcdef");
        tatActivity.setTatForLinehaul("abcdef");
        tatActivity.setNumberOfReshedules("5");

        List<TATBreachDueTo> tatBreachDueToList = new ArrayList<>();
        tatBreachDueToList.add(tatBreachDueTo);

        tatActivity.setTatBreachDueTo(tatBreachDueToList);

        this.mockMvc
                .perform(post("/api/v1/{clientId}/tatActivities", client.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(tatActivity).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_update_existing_tatActivity() throws Exception {

        TATBreachDueTo tatBreachDueTo = new TATBreachDueTo();
        tatBreachDueTo.setBizlog("abcdef");
        tatBreachDueTo.setCustomer("abcdef");
        tatBreachDueTo.setThirdPartyLogistics("abcdef");

        TATActivity initialTatActivity = new TATActivity();
        initialTatActivity.setTatForFirstMile("abcdef");
        initialTatActivity.setTatForLastMile("abcdef");
        initialTatActivity.setTatForLinehaul("abcdef");
        Client client = getClient();
        initialTatActivity.setClient(client);
        initialTatActivity.setNumberOfReshedules("5");

        List<TATBreachDueTo> tatBreachDueToList = new ArrayList<>();
        tatBreachDueToList.add(tatBreachDueTo);

        initialTatActivity.setTatBreachDueTo(tatBreachDueToList);
        initialTatActivity = tatActivityRepository.save(initialTatActivity);

        TATActivityDTO updatedTatActivity = getMapper().toDTO(initialTatActivity);
        updatedTatActivity.setTatForFirstMile("pqrst");
        updatedTatActivity.setTatForLastMile("pqrst");
        updatedTatActivity.setTatForLinehaul("xyz");
        updatedTatActivity.setNumberOfReshedules("3");
        this.mockMvc
                .perform(put("/api/v1/{clientId}/tatActivities/{id}", client.getId(), initialTatActivity.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedTatActivity).orElse("")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(toJson(updatedTatActivity).orElse("")));
    }

    @Test
    void should_not_update_existing_tatActivity() throws Exception {
        int clientId = 12;
        int id = 21;
        TATActivity updatedTatActivity = new TATActivity();
        updatedTatActivity.setTatForFirstMile("pqrst");
        updatedTatActivity.setTatForLastMile("pqrst");
        updatedTatActivity.setTatForLinehaul("xyz");
        updatedTatActivity.setNumberOfReshedules("3");
        this.mockMvc
                .perform(put("/api/v1/{clientId}/tatActivities/{id}", clientId, id)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedTatActivity).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_delete_existing_tatActivity() throws Exception {

        TATBreachDueTo tatBreachDueTo = new TATBreachDueTo();
        tatBreachDueTo.setBizlog("abcdef");
        tatBreachDueTo.setCustomer("abcdef");
        tatBreachDueTo.setThirdPartyLogistics("abcdef");

        TATActivity tatActivity = new TATActivity();
        tatActivity.setTatForFirstMile("abcdef");
        tatActivity.setTatForLastMile("abcdef");
        tatActivity.setTatForLinehaul("abcdef");
        tatActivity.setNumberOfReshedules("5");

        List<TATBreachDueTo> tatBreachDueToList = new ArrayList<>();
        tatBreachDueToList.add(tatBreachDueTo);

        tatActivity.setTatBreachDueTo(tatBreachDueToList);
        Client client = getClient();
        tatActivity.setClient(client);
        tatActivity = tatActivityRepository.save(tatActivity);

        this.mockMvc.perform(delete("/api/v1/{clientId}/tatActivities/{id}", client.getId(), tatActivity.getId()))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void should_not_delete_nonexistent_tatActivity() throws Exception {
        int clientId = 11;
        int nonexistentId = 999;
        this.mockMvc.perform(delete("/api/v1/{clientId}/tatActivities/{id}", clientId, nonexistentId)).andDo(print())
                .andExpect(status().isNotFound());
    }
}
