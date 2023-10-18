package com.bizlog.rms;

import com.bizlog.rms.entities.Client;

import com.bizlog.rms.entities.notification.Notification;
import com.bizlog.rms.repository.NotificationRepository;
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

import static com.bizlog.rms.utils.DataLoaderUtil.getNotification;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NotificationApiIT extends BaseApiTest {
    @Autowired
    private NotificationRepository notificationRepository;

    @BeforeEach
    void beforeEach() {
        Client client = DataLoaderUtil.getClient();
        client = clientRepository.save(client);
        getNotification(client).forEach(notificationRepository::save);
    }

    @AfterEach
    void afterEach() {
        notificationRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        int clientId = 1;
        int id = 1;
        this.mockMvc.perform(get("/api/v1/{clientId}/notification/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/{clientId}/notification/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_notification() throws Exception {
        int clientId = 1;
        Notification notification = new Notification();
        notification.setIsEmailRequired(true);
        notification.setIsSmsRequired(false);
        notification.setIsTicketScansRequired(false);
        notification.setIsReportAlertsRequired(true);
        notification.setIsAlertNeededForNegativeCases(false);
        this.mockMvc
                .perform(post("/api/v1/{clientId}/notification", clientId).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(notification)))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_not_create_new_notification() throws Exception {
        int clientId = 11;
        Notification notification = new Notification();
        notification.setIsEmailRequired(true);
        notification.setIsSmsRequired(false);
        notification.setIsTicketScansRequired(false);
        notification.setIsReportAlertsRequired(true);
        notification.setIsAlertNeededForNegativeCases(false);
        this.mockMvc
                .perform(post("/api/v1/{clientId}/notification", clientId).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(notification)))
                .andDo(print()).andExpect(status().isNotFound());
    }
}
