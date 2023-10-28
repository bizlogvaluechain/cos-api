package com.bizlog.rms;

import com.bizlog.rms.dto.locationService.LocationDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.location.Charge;
import com.bizlog.rms.entities.location.Location;
import com.bizlog.rms.entities.location.ServiceType;
import com.bizlog.rms.repository.LocationRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class LocationApiIT extends BaseApiIT {
    @Autowired
    private LocationRepository locationRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getLocations(getClient()).forEach(locationRepository::save);
    }

    @AfterEach
    void afterEach() {
        locationRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Long clientId = locationRepository.findAll().get(0).getclientId();
        Long id = locationRepository.findAll().get(0).getId();
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
        Client client = getClient();
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
                .perform(post("/api/v1/{clientId}/location", client.getId()).contentType(MediaType.APPLICATION_JSON)
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

    @Test
    void should_update_existing_location() throws Exception {
        Charge charge = new Charge();
        charge.setDeliverableArea(10L);
        charge.setNonDeliverableArea(15L);
        charge.setOutDeliverableArea(1L);

        ServiceType serviceType = new ServiceType();
        serviceType.setDeliverableArea("yes");
        serviceType.setOutDeliverableArea("yes");
        serviceType.setNonDeliverableArea("yes");

        Location initialLocation = new Location();
        List<Charge> charges = new ArrayList<>();
        charges.add(charge);
        initialLocation.setCharge(charges);
        initialLocation.setBizlogLocationMaster("ppl");
        List<ServiceType> serviceTypes = new ArrayList<>();
        serviceTypes.add(serviceType);
        initialLocation.setServiceType(serviceTypes);
        initialLocation.setSelectStates("up");
        initialLocation.setSelectCities("goa");
        Client client = getClient();
        initialLocation.setClient(client);
        initialLocation = locationRepository.save(initialLocation);

        LocationDTO updatedLocation = getMapper().toDTO(initialLocation);
        updatedLocation.setBizlogLocationMaster("up");
        updatedLocation.setSelectStates("bihar");
        updatedLocation.setSelectCities("patna");

        this.mockMvc
                .perform(put("/api/v1/{clientId}/location/{id}", client.getId(), initialLocation.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedLocation).orElse("")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(toJson(updatedLocation).orElse("")));
    }

    @Test
    void should_not_update_existing_location() throws Exception {
        int clientId = 11;
        long id = 199;

        Location updatedLocation = new Location();
        updatedLocation.setBizlogLocationMaster("up");
        updatedLocation.setSelectStates("bihar");
        updatedLocation.setSelectCities("patna");

        this.mockMvc.perform(put("/api/v1/{clientId}/location/{id}", clientId, id)
                .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedLocation).orElse(""))).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_delete_existing_location() throws Exception {
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
        Client client = getClient();
        location.setClient(client);
        location.setSelectCities("viz");
        location = locationRepository.save(location);
        this.mockMvc.perform(delete("/api/v1/{clientId}/location/{id}", client.getId(), location.getId()))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void should_not_delete_nonexistent_location() throws Exception {
        int clientId = 11;
        int nonexistentId = 999;
        this.mockMvc.perform(delete("/api/v1/{clientId}/location/{id}", clientId, nonexistentId)).andDo(print())
                .andExpect(status().isNotFound());
    }
}
