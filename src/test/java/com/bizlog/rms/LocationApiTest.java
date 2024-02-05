package com.bizlog.rms;

import com.bizlog.rms.dto.locationService.LocationDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.location.Location;
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

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class LocationApiTest extends BaseApiTest {
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
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/location/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/location/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_location() throws Exception {
        Client client = getClient();
        Location location = new Location();
        location.setCountries(Arrays.asList("India", "Usa"));
        location.setStates(Arrays.asList("Karnataka ", "up", "bihar"));
        location.setAreas(Arrays.asList("Urban", "costalarea"));
        location.setCities(Arrays.asList("Bangalore", "Delhi", "Goa"));
        location.setVehicle(Arrays.asList("threeVelor", "fourVelor", "bike"));
        location.setPinCodes(Arrays.asList("560001", "560028", "560029"));
        location.setTransportLinehaul("bizlog");
        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/location", client.getId()).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(location)))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_not_create_new_location() throws Exception {
        int clientId = 11;
        Location location = new Location();
        location.setCountries(Arrays.asList("India", "Usa"));
        location.setStates(Arrays.asList("Karnataka ", "up", "bihar"));
        location.setAreas(Arrays.asList("Urban", "costalarea"));
        location.setCities(Arrays.asList("Bangalore", "Delhi", "Goa"));
        location.setVehicle(Arrays.asList("threeVelor", "fourVelor", "bike"));
        location.setPinCodes(Arrays.asList("560001", "560028", "560029"));
        location.setTransportLinehaul("bizlog");
        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/notification", clientId).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(location)))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_update_existing_location() throws Exception {

        Location initialLocation = new Location();
        initialLocation.setCountries(Arrays.asList("India", "Usa"));
        initialLocation.setStates(Arrays.asList("Karnataka ", "up", "bihar"));
        initialLocation.setAreas(Arrays.asList("Urban", "costalArea"));
        initialLocation.setCities(Arrays.asList("Bangalore", "Delhi", "Goa"));
        initialLocation.setVehicle(Arrays.asList("threeVelor", "fourVelor", "bike"));
        initialLocation.setPinCodes(Arrays.asList("560001", "560028", "560029"));
        initialLocation.setTransportLinehaul("bizlog");
        Client client = getClient();
        initialLocation.setClient(client);
        initialLocation = locationRepository.save(initialLocation);

        LocationDTO updatedLocation = getMapper().toDTO(initialLocation);
        updatedLocation.setCities(Arrays.asList("bihar", "Punjab", "Goa"));
        updatedLocation.setVehicle(Arrays.asList("truck", "plane", "Air"));
        updatedLocation.setPinCodes(Arrays.asList("560055", "560085", "554029"));
        updatedLocation.setTransportLinehaul("bizlog");

        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/location/{id}", client.getId(), initialLocation.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedLocation).orElse("")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(toJson(updatedLocation).orElse("")));
    }

    @Test
    void should_not_update_existing_location() throws Exception {
        int clientId = 11;
        long id = 199;

        Location updatedLocation = new Location();
        updatedLocation.setCities(Arrays.asList("bihar", "Punjab", "Goa"));
        updatedLocation.setVehicle(Arrays.asList("truck", "plane", "Air"));
        updatedLocation.setPinCodes(Arrays.asList("560055", "560085", "554029"));
        updatedLocation.setTransportLinehaul("bizlog");

        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/location/{id}", clientId, id)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedLocation).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_delete_existing_location() throws Exception {

        Location location = new Location();
        location.setCountries(Arrays.asList("India", "Usa"));
        location.setStates(Arrays.asList("Karnataka ", "up", "bihar"));
        location.setAreas(Arrays.asList("Urban", "costalArea"));
        location.setCities(Arrays.asList("Bangalore", "Delhi", "Goa"));
        location.setVehicle(Arrays.asList("threeVelor", "fourVelor", "bike"));
        location.setPinCodes(Arrays.asList("560001", "560028", "560029"));
        location.setTransportLinehaul("bizlog");
        Client client = getClient();
        location.setClient(client);
        location = locationRepository.save(location);
        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/location/{id}", client.getId(), location.getId()))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void should_not_delete_nonexistent_location() throws Exception {
        int clientId = 11;
        int nonexistentId = 999;
        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/location/{id}", clientId, nonexistentId)).andDo(print())
                .andExpect(status().isNotFound());
    }
}
