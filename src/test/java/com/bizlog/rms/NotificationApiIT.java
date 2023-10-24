package com.bizlog.rms;

import com.bizlog.rms.dto.notification.NotificationDTO;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NotificationApiIT extends BaseApiTest {
    @Autowired
    private NotificationRepository notificationRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getNotification(getClient()).forEach(notificationRepository::save);
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
        this.mockMvc.perform(post("/api/v1/{clientId}/notification", clientId).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(notification).orElse(""))).andDo(print()).andExpect(status().is2xxSuccessful());
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

    @Test
    void should_update_existing_notification() throws Exception {
        int clientId = 1;
        long id = 1;
        Notification initialNotification = new Notification();
        initialNotification.setIsEmailRequired(true);
        initialNotification.setIsSmsRequired(false);
        initialNotification.setIsTicketScansRequired(false);
        initialNotification.setIsReportAlertsRequired(true);
        initialNotification.setIsAlertNeededForNegativeCases(false);
        initialNotification.setClient(getClient());
        initialNotification = notificationRepository.save(initialNotification);

        NotificationDTO updateNotification = getMapper().toDTO(initialNotification);
        updateNotification.setIsEmailRequired(false);
        updateNotification.setIsSmsRequired(true);
        updateNotification.setIsTicketScansRequired(true);
        updateNotification.setIsReportAlertsRequired(false);
        updateNotification.setIsAlertNeededForNegativeCases(true);
        this.mockMvc
                .perform(put("/api/v1/{clientId}/notification/{id}", clientId, id)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updateNotification).orElse("")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(toJson(updateNotification).orElse("")));
    }

    @Test
    void should_not_update_existing_notification() throws Exception {
        int clientId = 99;
        long id = 991;

        Notification updateNotification = new Notification();
        updateNotification.setIsEmailRequired(false);
        updateNotification.setIsSmsRequired(true);
        updateNotification.setIsTicketScansRequired(true);
        updateNotification.setIsReportAlertsRequired(false);
        updateNotification.setIsAlertNeededForNegativeCases(true);
        this.mockMvc
                .perform(put("/api/v1/{clientId}/notification/{id}", clientId, id)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updateNotification).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_delete_existing_notification() throws Exception {
        Notification notification = new Notification();
        notification.setIsEmailRequired(true);
        notification.setIsSmsRequired(false);
        notification.setIsTicketScansRequired(false);
        notification.setIsReportAlertsRequired(true);
        notification.setIsAlertNeededForNegativeCases(false);
        Client client = getClient();
        notification.setClient(client);
        notification = notificationRepository.save(notification);
        this.mockMvc.perform(delete("/api/v1/{clientId}/notification/{id}", client.getId(), notification.getId()))
                .andDo(print()).andExpect(status().isNoContent());
    }
}
