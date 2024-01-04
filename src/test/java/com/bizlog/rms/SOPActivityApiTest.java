package com.bizlog.rms;

import com.bizlog.rms.dto.SOP_TAT.SOPActivityDTO;
import com.bizlog.rms.dto.SOP_TAT.subLists.MajorActivites;
import com.bizlog.rms.dto.SOP_TAT.subLists.MinorActivites;
import com.bizlog.rms.entities.Client;

import com.bizlog.rms.entities.sop.SOPActivity;
import com.bizlog.rms.repository.SOPActivityRepository;
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
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class SOPActivityApiTest extends BaseApiTest {
    @Autowired
    private SOPActivityRepository sopActivityRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getSOPActivity(getClient()).forEach(sopActivityRepository::save);
    }

    @AfterEach
    void afterEach() {
        sopActivityRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Long clientId = sopActivityRepository.findAll().get(0).getclientId();
        Long id = sopActivityRepository.findAll().get(0).getId();
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/sop/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/sop/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_sopActivity() throws Exception {
        Client client = getClient();
        MajorActivites majorActivites = new MajorActivites();
        majorActivites.setSPS(Collections.singletonList("IDP"));
        majorActivites.setLinehaul(Collections.singletonList("IDP"));
        majorActivites.setFullTruckLoad(Collections.singletonList("IDP"));
        majorActivites.setMultiPiceShipment(Collections.singletonList("IDP"));
        majorActivites.setLessThanTruckLoad(Collections.singletonList("IDP"));
        majorActivites.setMultiProductShipment(Collections.singletonList("IDP"));
        majorActivites.setSPS(Collections.singletonList("IDP"));

        MinorActivites minorActivites = new MinorActivites();
        minorActivites.setQC(Collections.singletonList("IDP"));
        minorActivites.setEvaluation(Collections.singletonList("IDP"));
        minorActivites.setGrading(Collections.singletonList("IDP"));
        minorActivites.setSegregation(Collections.singletonList("IDP"));
        minorActivites.setImageCapture(Collections.singletonList("IDP"));
        minorActivites.setOTPValidation(Collections.singletonList("IDP"));
        minorActivites.setSignatureCapture(Collections.singletonList("IDP"));

        SOPActivity sopActivity = new SOPActivity();

        List<MajorActivites> majorActivitesList = new ArrayList<>();
        majorActivitesList.add(majorActivites);

        List<MinorActivites> minorActivitesList = new ArrayList<>();
        minorActivitesList.add(minorActivites);

        sopActivity.setMajorActivites(majorActivitesList);
        sopActivity.setMinorActivites(minorActivitesList);
        this.mockMvc.perform(post("/api/v1/cos/{clientId}/sop", client.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(sopActivity).orElse(""))).andDo(print()).andExpect(status().is2xxSuccessful());

    }

    @Test
    void should_update_existing_sopActivity() throws Exception {

        MajorActivites majorActivites = new MajorActivites();
        majorActivites.setSPS(Collections.singletonList("IDP"));
        majorActivites.setLinehaul(Collections.singletonList("IDP"));
        majorActivites.setFullTruckLoad(Collections.singletonList("IDP"));
        majorActivites.setMultiPiceShipment(Collections.singletonList("IDP"));
        majorActivites.setLessThanTruckLoad(Collections.singletonList("IDP"));
        majorActivites.setMultiProductShipment(Collections.singletonList("IDP"));
        majorActivites.setSPS(Collections.singletonList("IDP"));

        MinorActivites minorActivites = new MinorActivites();
        minorActivites.setQC(Collections.singletonList("IDP"));
        minorActivites.setEvaluation(Collections.singletonList("IDP"));
        minorActivites.setGrading(Collections.singletonList("IDP"));
        minorActivites.setSegregation(Collections.singletonList("IDP"));
        minorActivites.setImageCapture(Collections.singletonList("IDP"));
        minorActivites.setOTPValidation(Collections.singletonList("IDP"));
        minorActivites.setSignatureCapture(Collections.singletonList("IDP"));

        SOPActivity initialSopActivity = new SOPActivity();

        List<MajorActivites> majorActivitesList = new ArrayList<>();
        majorActivitesList.add(majorActivites);

        List<MinorActivites> minorActivitesList = new ArrayList<>();
        minorActivitesList.add(minorActivites);

        initialSopActivity.setMajorActivites(majorActivitesList);
        initialSopActivity.setMinorActivites(minorActivitesList);
        Client client = getClient();
        initialSopActivity.setClient(client);
        initialSopActivity = sopActivityRepository.save(initialSopActivity);

        MajorActivites updatedMajorActivites = new MajorActivites();
        updatedMajorActivites.setSPS(Collections.singletonList("DPI"));
        updatedMajorActivites.setLinehaul(Collections.singletonList("DPI"));
        updatedMajorActivites.setFullTruckLoad(Collections.singletonList("DPI"));
        updatedMajorActivites.setMultiPiceShipment(Collections.singletonList("V"));
        updatedMajorActivites.setLessThanTruckLoad(Collections.singletonList("DPI"));
        updatedMajorActivites.setMultiProductShipment(Collections.singletonList("DPI"));
        updatedMajorActivites.setSPS(Collections.singletonList("DPI"));

        SOPActivityDTO updatedSopActivity = getMapper().toDTO(initialSopActivity);

        List<MajorActivites> updatedMajorActivitesList = new ArrayList<>();
        updatedMajorActivitesList.add(updatedMajorActivites);
        updatedSopActivity.setMajorActivites(updatedMajorActivitesList);
        updatedSopActivity.setMinorActivites(minorActivitesList);

        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/sop/{id}", client.getId(), initialSopActivity.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updatedSopActivity).orElse("")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(toJson(updatedSopActivity).orElse("")));

    }

    @Test
    void should_not_update_existing_sopActivity() throws Exception {
        int clientId = 21;
        long id = 299;

        MinorActivites minorActivites = new MinorActivites();
        minorActivites.setQC(Collections.singletonList("IDP"));
        minorActivites.setEvaluation(Collections.singletonList("IDP"));
        minorActivites.setGrading(Collections.singletonList("IDP"));
        minorActivites.setSegregation(Collections.singletonList("IDP"));
        minorActivites.setImageCapture(Collections.singletonList("IDP"));
        minorActivites.setOTPValidation(Collections.singletonList("IDP"));
        minorActivites.setSignatureCapture(Collections.singletonList("IDP"));

        List<MinorActivites> minorActivitesList = new ArrayList<>();
        minorActivitesList.add(minorActivites);

        MajorActivites updatedMajorActivites = new MajorActivites();
        updatedMajorActivites.setSPS(Collections.singletonList("DPI"));
        updatedMajorActivites.setLinehaul(Collections.singletonList("DPI"));
        updatedMajorActivites.setFullTruckLoad(Collections.singletonList("DPI"));
        updatedMajorActivites.setMultiPiceShipment(Collections.singletonList("V"));
        updatedMajorActivites.setLessThanTruckLoad(Collections.singletonList("DPI"));
        updatedMajorActivites.setMultiProductShipment(Collections.singletonList("DPI"));
        updatedMajorActivites.setSPS(Collections.singletonList("DPI"));

        SOPActivity updatedSopActivity = new SOPActivity();

        List<MajorActivites> updatedMajorActivitesList = new ArrayList<>();
        updatedMajorActivitesList.add(updatedMajorActivites);
        updatedSopActivity.setMajorActivites(updatedMajorActivitesList);
        updatedSopActivity.setMinorActivites(minorActivitesList);

        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/sop/{id}", clientId, id).contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(updatedSopActivity).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());

    }

    @Test
    void should_delete_existing_sopActivity() throws Exception {

        MajorActivites majorActivites = new MajorActivites();
        majorActivites.setSPS(Collections.singletonList("IDP"));
        majorActivites.setLinehaul(Collections.singletonList("IDP"));
        majorActivites.setFullTruckLoad(Collections.singletonList("IDP"));
        majorActivites.setMultiPiceShipment(Collections.singletonList("IDP"));
        majorActivites.setLessThanTruckLoad(Collections.singletonList("IDP"));
        majorActivites.setMultiProductShipment(Collections.singletonList("IDP"));
        majorActivites.setSPS(Collections.singletonList("IDP"));

        MinorActivites minorActivites = new MinorActivites();
        minorActivites.setQC(Collections.singletonList("IDP"));
        minorActivites.setEvaluation(Collections.singletonList("IDP"));
        minorActivites.setGrading(Collections.singletonList("IDP"));
        minorActivites.setSegregation(Collections.singletonList("IDP"));
        minorActivites.setImageCapture(Collections.singletonList("IDP"));
        minorActivites.setOTPValidation(Collections.singletonList("IDP"));
        minorActivites.setSignatureCapture(Collections.singletonList("IDP"));

        SOPActivity sopActivity = new SOPActivity();

        List<MajorActivites> majorActivitesList = new ArrayList<>();
        majorActivitesList.add(majorActivites);

        List<MinorActivites> minorActivitesList = new ArrayList<>();
        minorActivitesList.add(minorActivites);

        sopActivity.setMajorActivites(majorActivitesList);
        sopActivity.setMinorActivites(minorActivitesList);
        Client client = getClient();
        sopActivity.setClient(client);
        sopActivity = sopActivityRepository.save(sopActivity);
        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/sop/{id}", client.getId(), sopActivity.getId()))
                .andDo(print()).andExpect(status().isNoContent());

    }

    @Test
    void should_not_delete_nonexistent_sopActivity() throws Exception {
        int clientId = 11;
        int nonexistentId = 999;
        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/sop/{id}", clientId, nonexistentId)).andDo(print())
                .andExpect(status().isNotFound());
    }

}
