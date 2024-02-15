package com.bizlog.rms;

import com.bizlog.rms.dto.frequency.FrequencyDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.sop.frequency.Frequency;
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
        DataLoaderUtil.getFrequency(getOrganization()).forEach(frequencyRepository::save);
    }

    @AfterEach
    void afterEach() {
        frequencyRepository.deleteAll();
        organizationRepository.deleteAll();

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
        Organization organization = getOrganization();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("frequency==100");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);
        assertThat(frequency, isIn(results));
    }

    @Test
    void givenTicketsVolume_whenGettingListOf_thenInCorrect() {
        Organization organization = getOrganization();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("frequency==100");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);
        assertThat(frequency, not(results));
    }

    @Test
    public void givenTicketsVolume_whenGettingListOfUsers_thenCorrect() {

        Organization organization = getOrganization();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("frequency!=500");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, isIn(results));
    }

    @Test
    public void givenTicketsVolume_whenGettingListOfUsers_thenInCorrect() {

        Organization organization = getOrganization();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("frequency!=500");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, not(results));
    }

    @Test
    public void givenMinTicketsVolume_whenGettingListOfUsers_thenCorrect() {

        Organization organization = getOrganization();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("frequency>80");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, not(results));
    }

    @Test
    public void givenMaxTicketsVolume_whenGettingListOfUsers_thenCorrect() {

        Organization organization = getOrganization();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("frequency<80");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, not(results));
    }

    @Test
    public void givenTicketsVolumePrefix_whenGettingListOfUsers_thenCorrect() {

        Organization organization = getOrganization();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("frequency==100");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, isIn(results));
    }

    @Test
    public void givenTicketsVolumePrefix_whenGettingListOfUsers_thenInCorrect() {

        Organization organization = getOrganization();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("frequency==500");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, not(results));
    }

    @Test
    public void ListOfTicketsVolume_whenGettingListOfUsers_thenCorrect() {

        Organization organization = getOrganization();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("frequency=in=(100,150)");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, isIn(results));
    }

    @Test
    public void ListOfTicketsVolume_whenGettingListOfUsers_thenInCorrect() {

        Organization organization = getOrganization();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("frequencyUnit=in=(100,120)");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, not(results));
    }

    @Test
    void should_create_new_frequency() throws Exception {
        Organization organization = getOrganization();
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setOrganization(organization);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);

        this.mockMvc
                .perform(post("/api/v1/cos/{clientId}/frequency", organization.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(frequency).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_not_create_new_frequency() throws Exception {
        int clientId = 11;
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setFrequencyUnit("abc");
        frequency.setFrequency(100L);
        frequency.setActivityStartdate(23122023L);
        frequency.setActivityEndDate(23122023L);
        frequency.setOperationStartTime(23122023L);
        frequency.setOperationEndTime(23122023L);
        frequency.setOperationsOnbizlogHolidays(true);
        frequency.setOperationsOnStatutoryHolidays(false);
        frequency.setOperatoinsOnClientHolidays(true);
        frequency.setOperationDays(5L);
        this.mockMvc.perform(post("/api/v1/cos/{clientId}/frequency", clientId).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(frequency).orElse(""))).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_update_existing_frequency() throws Exception {

        Frequency intialFrequency = new Frequency();
        intialFrequency.setFrequency(100L);
        intialFrequency.setFrequencyUnit("abc");
        intialFrequency.setActivityEndDate(23122023L);
        intialFrequency.setActivityStartdate(23122023L);
        intialFrequency.setOperationStartTime(23122023L);
        intialFrequency.setOperationEndTime(23122023L);
        intialFrequency.setOperationsOnbizlogHolidays(true);
        intialFrequency.setOperationsOnStatutoryHolidays(false);
        intialFrequency.setOperatoinsOnClientHolidays(true);
        intialFrequency.setOperationDays(5L);
        Organization organization = getOrganization();
        intialFrequency.setOrganization(organization);
        intialFrequency = frequencyRepository.save(intialFrequency);

        FrequencyDTO updateFrequency = getMapper().toDTO(intialFrequency);
        updateFrequency.setFrequencyUnit("abc");
        updateFrequency.setActivityEndDate(232023L);
        updateFrequency.setActivityStartdate(231220L);
        updateFrequency.setOperationStartTime(23122023L);
        updateFrequency.setOperationEndTime(23122023L);

        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/frequency/{id}", organization.getId(), intialFrequency.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updateFrequency).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().json(toJson(updateFrequency).orElse("")));
    }

    @Test
    void should_not_update_existing_frequency() throws Exception {
        int clientId = 111;
        Long id = 999L;
        Frequency frequency = new Frequency();
        frequency.setFrequencyUnit("Abc");
        frequency.setActivityEndDate(23122023L);

        frequency.setOperationDays(5L);
        frequency.setFrequency(100L);
        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/frequency/{id}", clientId, id)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(frequency).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_delete_existing_frequency() throws Exception {

        Frequency intialFrequency = new Frequency();
        intialFrequency.setFrequency(100L);
        intialFrequency.setFrequencyUnit("abc");
        intialFrequency.setActivityEndDate(23122023L);
        intialFrequency.setActivityStartdate(23122023L);
        intialFrequency.setOperationStartTime(23122023L);
        intialFrequency.setOperationEndTime(23122023L);
        intialFrequency.setOperationsOnbizlogHolidays(true);
        intialFrequency.setOperationsOnStatutoryHolidays(false);
        intialFrequency.setOperatoinsOnClientHolidays(true);
        intialFrequency.setOperationDays(5L);
        Organization organization = getOrganization();
        intialFrequency.setOrganization(organization);
        intialFrequency = frequencyRepository.save(intialFrequency);

        this.mockMvc
                .perform(delete("/api/v1/cos/{clientId}/frequency/{id}", organization.getId(), intialFrequency.getId()))
                .andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void should_not_delete_non_existent_frequency() throws Exception {
        int clientId = 11;
        int nonexistentId = 999;
        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/frequency/{id}", clientId, nonexistentId)).andDo(print())
                .andExpect(status().isNotFound());
    }

}
