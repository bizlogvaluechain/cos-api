package com.bizlog.rms;

import com.bizlog.rms.dto.SOP_TAT.subLists.TATBreachDueTo;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.Specifications.TATActivity;
import com.bizlog.rms.repository.TATActivityRepository;
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

public class TATActivityApiIT extends BaseApiTest {
    @Autowired
    private TATActivityRepository tatActivityRepository;

    @BeforeEach
    void beforeEach() {
        Client client = DataLoaderUtil.getClient();
        client = clientRepository.save(client);
        DataLoaderUtil.getTATActivity(client).forEach(tatActivityRepository::save);
    }

    @AfterEach
    void afterEach() {
        clientRepository.deleteAll();
        tatActivityRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        int clientId = 1;
        int id = 1;
        this.mockMvc.perform(get("/api/v1/{clientId}/sop/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/{clientId}/sop/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_tatActivity() throws Exception {
        int clientId = 1;
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
                .perform(post("/api/v1/{clientId}/tatActivities",clientId).contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(tatActivity).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }
}
