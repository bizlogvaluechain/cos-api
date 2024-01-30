package com.bizlog.rms;

import com.bizlog.rms.dto.frequency.FrequencyDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.sop.frequency.Frequency;
import com.bizlog.rms.entities.sop.frequency.FrequencyUnit;
import com.bizlog.rms.entities.sop.frequency.HolidayApplicable;
import com.bizlog.rms.repository.FrequencyRepository;
import com.bizlog.rms.rsql.CustomRsqlVisitor;
import com.bizlog.rms.utils.DataLoaderUtil;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class FrequencyApiTest extends BaseApiTest {

    @Autowired
    private FrequencyRepository frequencyRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getFrequency(getClient()).forEach(frequencyRepository::save);
    }

    @AfterEach
    void afterEach() {
        frequencyRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Long clientId = frequencyRepository.findAll().get(0).getclientId();
        Long id = frequencyRepository.findAll().get(0).getId();
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/frequency/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/frequency/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void givenTicketsVolume_whenGettingListOf_thenCorrect() {
        Client client = getClient();
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume==100");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);
        assertThat(frequency, isIn(results));
    }

    @Test
    void givenTicketsVolume_whenGettingListOf_thenInCorrect() {
        Client client = getClient();
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);

        frequency.setHolidayApplicable(holidayApplicable);
        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume==100");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);
        assertThat(frequency, not(results));
    }

    @Test
    public void givenTicketsVolume_whenGettingListOfUsers_thenCorrect() {

        Client client = getClient();
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);

        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume!=500");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, isIn(results));
    }

    @Test
    public void givenTicketsVolume_whenGettingListOfUsers_thenInCorrect() {

        Client client = getClient();
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);

        frequency.setHolidayApplicable(holidayApplicable);
        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume!=500");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, not(results));
    }

    @Test
    public void givenMinTicketsVolume_whenGettingListOfUsers_thenCorrect() {

        Client client = getClient();
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);

        frequency.setHolidayApplicable(holidayApplicable);
        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume>80");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, not(results));
    }

    @Test
    public void givenMaxTicketsVolume_whenGettingListOfUsers_thenCorrect() {

        Client client = getClient();
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);

        frequency.setHolidayApplicable(holidayApplicable);
        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume<80");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, not(results));
    }

    @Test
    public void givenTicketsVolumePrefix_whenGettingListOfUsers_thenCorrect() {

        Client client = getClient();
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume==10*");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, isIn(results));
    }

    @Test
    public void givenTicketsVolumePrefix_whenGettingListOfUsers_thenInCorrect() {

        Client client = getClient();
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setHolidayApplicable(holidayApplicable);
        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume==50*");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, not(results));
    }

    @Test
    public void ListOfTicketsVolume_whenGettingListOfUsers_thenCorrect() {

        Client client = getClient();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume=in=(100,150)");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, isIn(results));
    }

    @Test
    public void ListOfTicketsVolume_whenGettingListOfUsers_thenInCorrect() {

        Client client = getClient();
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setHolidayApplicable(holidayApplicable);
        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume=in=(100,120)");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, not(results));
    }

    @Test
    void should_create_new_frequency() throws Exception {
        Client client = getClient();
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setHolidayApplicable(holidayApplicable);
        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");

        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/frequency", client.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(frequency).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_not_create_new_frequency() throws Exception {
        int clientId = 11;
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);
        frequency.setStartDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setHolidayApplicable(holidayApplicable);
        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        this.mockMvc.perform(post("/api/v1/cos/{clientId}/frequency", clientId).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(frequency).orElse(""))).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_update_existing_frequency() throws Exception {
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency intialFrequency = new Frequency();
        intialFrequency.setUnit(FrequencyUnit.PER_DAY);
        intialFrequency.setEndDate(23122023L);
        intialFrequency.setStartDate(23122023L);
        intialFrequency.setOperationStartTime(23122023L);
        intialFrequency.setOperationEndTime(23122023L);
        intialFrequency.setHolidayApplicable(holidayApplicable);
        intialFrequency.setOperationDay(5L);
        intialFrequency.setTicketsVolume("100");
        Client client = getClient();
        intialFrequency.setClient(client);
        intialFrequency = frequencyRepository.save(intialFrequency);

        FrequencyDTO updateFrequency = getMapper().toDTO(intialFrequency);
        updateFrequency.setUnit(FrequencyUnit.PER_MONTH);
        updateFrequency.setEndDate(23122023L);
        updateFrequency.setStartDate(23122023L);
        updateFrequency.setOperationStartTime(23122023L);
        updateFrequency.setOperationEndTime(23122023L);

        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/frequency/{id}", client.getId(), intialFrequency.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updateFrequency).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().json(toJson(updateFrequency).orElse("")));
    }

    @Test
    void should_not_update_existing_frequency() throws Exception {
        int clientId = 111;
        Long id = 999L;
        Frequency frequency = new Frequency();
        frequency.setUnit(FrequencyUnit.PER_DAY);
        frequency.setEndDate(23122023L);

        frequency.setOperationDay(5L);
        frequency.setTicketsVolume("100");
        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/frequency/{id}", clientId, id)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(frequency).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_delete_existing_ticket_creation_config() throws Exception {
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency intialFrequency = new Frequency();
        intialFrequency.setUnit(FrequencyUnit.PER_DAY);
        intialFrequency.setEndDate(23122023L);
        intialFrequency.setStartDate(23122023L);
        intialFrequency.setOperationStartTime(23122023L);
        intialFrequency.setOperationEndTime(23122023L);
        intialFrequency.setHolidayApplicable(holidayApplicable);
        intialFrequency.setOperationDay(5L);
        intialFrequency.setTicketsVolume("100");
        Client client = getClient();
        intialFrequency.setClient(client);
        intialFrequency = frequencyRepository.save(intialFrequency);

        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/frequency/{id}", client.getId(), intialFrequency.getId()))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void should_not_delete_non_existent_ticket_creation_config() throws Exception {
        int clientId = 11;
        int nonexistentId = 999;
        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/frequency/{id}", clientId, nonexistentId))
                .andDo(print()).andExpect(status().isNotFound());
    }

}
