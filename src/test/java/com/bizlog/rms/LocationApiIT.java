package com.bizlog.rms;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.location.Charge;
import com.bizlog.rms.entities.location.Location;
import com.bizlog.rms.entities.location.ServiceType;
import com.bizlog.rms.entities.notification.Notification;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.repository.LocationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class LocationApiIT extends BaseApiTest {
    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    void beforeEach() {
        Client client = DataLoaderUtil.getClient();
        client = clientRepository.save(client);
        DataLoaderUtil.getLocations(client).forEach(locationRepository::save);
    }

    @AfterEach
    void afterEach() {
        locationRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        int clientId = 1;
        int id = 1;
        this.mockMvc.perform(get("/api/v1/{clientId}/location/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/{clientId}/location/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_location() throws Exception {
        int clientId = 1;
        Charge charge = new Charge();
        charge.setDeliverableArea(10L);
        charge.setNonDeliverableArea(10L);
        charge.setOutDeliverableArea(10L);

        ServiceType serviceType = new ServiceType();
        serviceType.setDeliverableArea("yes");
        serviceType.setOutDeliverableArea("yes");
        serviceType.setNonDeliverableArea("yes");

        Location location = new Location();
        List<Charge> charges = new ArrayList<>();
        charges.add(charge);
        location.setCharge(charges);
        location.setBizlogLocationMaster("IDP");
        List<ServiceType> serviceTypes = new ArrayList<>();
        serviceTypes.add(serviceType);
        location.setServiceType(serviceTypes);
        location.setSelectStates("AP");
        location.setSelectCities("viz");
        this.mockMvc
                .perform(post("/api/v1/{clientId}/location", clientId).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(location)))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_not_create_new_location() throws Exception {
        int clientId = 11;
        Charge charge = new Charge();
        charge.setDeliverableArea(10L);
        charge.setNonDeliverableArea(10L);
        charge.setOutDeliverableArea(10L);

        ServiceType serviceType = new ServiceType();
        serviceType.setDeliverableArea("yes");
        serviceType.setOutDeliverableArea("yes");
        serviceType.setNonDeliverableArea("yes");

        Location location = new Location();
        List<Charge> charges = new ArrayList<>();
        charges.add(charge);
        location.setCharge(charges);
        location.setBizlogLocationMaster("IDP");
        List<ServiceType> serviceTypes = new ArrayList<>();
        serviceTypes.add(serviceType);
        location.setServiceType(serviceTypes);
        location.setSelectStates("AP");
        location.setSelectCities("viz");
        this.mockMvc
                .perform(post("/api/v1/{clientId}/notification", clientId).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(location)))
                .andDo(print()).andExpect(status().isNotFound());
    }
}
