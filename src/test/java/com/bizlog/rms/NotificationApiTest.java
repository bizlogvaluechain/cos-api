package com.bizlog.rms;

import com.bizlog.rms.dto.notification.NotificationDTO;
import com.bizlog.rms.entities.Organization;

import com.bizlog.rms.entities.sop.notification.Notification;
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

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class NotificationApiTest extends BaseApiTest {
    @Autowired
    private NotificationRepository notificationRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getNotification(getOrganization()).forEach(notificationRepository::save);
    }

    @AfterEach
    void afterEach() {
        notificationRepository.deleteAll();
        organizationRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Long clientId = notificationRepository.findAll().get(0).getclientId();
        Long id = notificationRepository.findAll().get(0).getId();
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/notification/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/notification/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_notification() throws Exception {
        Organization organization = getOrganization();
        Notification notification = new Notification();
        notification.setSms(Arrays.asList("client", "customer"));
        notification.setEmail(Arrays.asList("client", "customer"));

        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/notification", organization.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(notification).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_not_create_new_notification() throws Exception {
        int clientId = 11;
        Notification notification = new Notification();
        notification.setSms(Arrays.asList("client", "customer"));
        notification.setEmail(Arrays.asList("client", "customer"));

        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/notification", clientId).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(notification)))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_update_existing_notification() throws Exception {

        Notification initialNotification = new Notification();
        initialNotification.setSms(Arrays.asList("client", "customer"));
        initialNotification.setEmail(Arrays.asList("client", "customer"));
        Organization organization = getOrganization();
        initialNotification.setOrganization(organization);
        initialNotification = notificationRepository.save(initialNotification);

        NotificationDTO updateNotification = getMapper().toDTO(initialNotification);
        updateNotification.setSms(Arrays.asList("client"));
        this.mockMvc.perform(
                put("/api/v1/cos/{clientId}/notification/{id}", organization.getId(), initialNotification.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updateNotification).orElse("")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(toJson(updateNotification).orElse("")));
    }

    @Test
    void should_not_update_existing_notification() throws Exception {
        int clientId = 99;
        long id = 991;

        Notification updateNotification = new Notification();
        updateNotification.setSms(Arrays.asList("client"));
        updateNotification.setEmail(Arrays.asList("customer"));
        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/notification/{id}", clientId, id)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updateNotification).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_delete_existing_notification() throws Exception {
        Notification notification = new Notification();
        notification.setSms(Arrays.asList("client"));
        notification.setEmail(Arrays.asList("customer"));
        Organization organization = getOrganization();
        notification.setOrganization(organization);
        notification = notificationRepository.save(notification);
        this.mockMvc
                .perform(delete("/api/v1/cos/{clientId}/notification/{id}", organization.getId(), notification.getId()))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void should_not_delete_nonexistent_notification() throws Exception {
        int clientId = 11;
        int nonexistentId = 999;
        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/notification/{id}", clientId, nonexistentId)).andDo(print())
                .andExpect(status().isNotFound());
    }
}
