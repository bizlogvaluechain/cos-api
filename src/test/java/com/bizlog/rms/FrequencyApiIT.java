package com.bizlog.rms;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.frequency.Frequency;
import com.bizlog.rms.entities.frequency.HolidayApplicable;
import com.bizlog.rms.repository.FrequencyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class FrequencyApiIT extends BaseApiTest {

    @Autowired
    private FrequencyRepository frequencyRepository;

    @Autowired
   private ObjectMapper objectMapper;
    @BeforeEach
    void beforeEach() {
        Client client = DataLoaderUtil.getClient();
        client = clientRepository.save(client);
        DataLoaderUtil.getFrequency(client).forEach(frequencyRepository::save);
    }

    @AfterEach
    void afterEach() {
        frequencyRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        int clientId = 1;
        int id = 1;
        this.mockMvc.perform(get("/api/v1/{clientId}/frequency/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/{clientId}/frequency/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

//    @Test
//    void should_create_new_frequency() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        int clientId = 1;
//        HolidayApplicable holidayApplicable = new HolidayApplicable();
//        holidayApplicable.setBizlogHolidays(true);
//        holidayApplicable.setPublicHolidays(true);
//        holidayApplicable.setClientHolidaays(false);
//        Frequency frequency = new Frequency();
//        frequency.setDayEndTime(LocalDateTime.of(2023, 10, 13, 0, 0));
//        frequency.setDayStartTime(LocalDateTime.of(2023, 10, 13, 0, 0));
//        frequency.setOnboardingDate(LocalDateTime.of(2023, 10, 13, 0, 0));
//        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
//        holidayApplicables.add(holidayApplicable);
//        frequency.setHolidayApplicable(holidayApplicables);
//        frequency.setOnlyWorkdays(true);
//        frequency.setOnlyWorkdays(true);
//        frequency.setTicketsVolume("100");
//        this.mockMvc
//                .perform(post("/api/v1/{clientId}/frequency", clientId).contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(frequency)))
//                .andDo(print()).andExpect(status().is2xxSuccessful());
//    }
//
//    @Test
//    void should_not_create_new_frequency() throws Exception {
//        int clientId = 11;
//        HolidayApplicable holidayApplicable = new HolidayApplicable();
//        holidayApplicable.setBizlogHolidays(true);
//        holidayApplicable.setPublicHolidays(true);
//        holidayApplicable.setClientHolidaays(false);
//        Frequency frequency = new Frequency();
//        frequency.setDayEndTime(LocalDateTime.of(2023, 10, 12, 0, 0));
//        frequency.setDayStartTime(LocalDateTime.of(2023, 10, 12, 0, 0));
//        frequency.setOnboardingDate(LocalDateTime.of(2023, 10, 12, 0, 0));
//        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
//        holidayApplicables.add(holidayApplicable);
//        frequency.setHolidayApplicable(holidayApplicables);
//        frequency.setOnlyWorkdays(true);
//        frequency.setOnlyWorkdays(true);
//        frequency.setTicketsVolume("100");
//        this.mockMvc
//                .perform(post("/api/v1/{clientId}/frequency", clientId).contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(frequency)))
//                .andDo(print()).andExpect(status().isNotFound());
//    }
}
