package com.bizlog.rms;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.frequency.Frequency;
import com.bizlog.rms.entities.frequency.HolidayApplicable;
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
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        frequency.setDayEndTime(23122023L);
        frequency.setDayStartTime(23122023L);
        frequency.setOnboardingDate(23122023L);
        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
        holidayApplicables.add(holidayApplicable);
        frequency.setHolidayApplicable(holidayApplicables);
        frequency.setOnlyWorkdays(true);
        frequency.setOnlyWorkdays(true);
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
        frequency.setDayEndTime(23122023L);
        frequency.setDayStartTime(23122023L);
        frequency.setOnboardingDate(23122023L);
        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
        holidayApplicables.add(holidayApplicable);
        frequency.setHolidayApplicable(holidayApplicables);
        frequency.setOnlyWorkdays(true);
        frequency.setOnlyWorkdays(true);
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
        frequency.setDayEndTime(23122023L);
        frequency.setDayStartTime(23122023L);
        frequency.setOnboardingDate(23122023L);
        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
        holidayApplicables.add(holidayApplicable);
        frequency.setHolidayApplicable(holidayApplicables);
        frequency.setOnlyWorkdays(true);
        frequency.setOnlyWorkdays(true);
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
        frequency.setDayEndTime(23122023L);
        frequency.setDayStartTime(23122023L);
        frequency.setOnboardingDate(23122023L);
        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
        holidayApplicables.add(holidayApplicable);
        frequency.setHolidayApplicable(holidayApplicables);
        frequency.setOnlyWorkdays(true);
        frequency.setOnlyWorkdays(true);
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
        frequency.setDayEndTime(23122023L);
        frequency.setDayStartTime(23122023L);
        frequency.setOnboardingDate(23122023L);
        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
        holidayApplicables.add(holidayApplicable);
        frequency.setHolidayApplicable(holidayApplicables);
        frequency.setOnlyWorkdays(true);
        frequency.setOnlyWorkdays(true);
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
        frequency.setDayEndTime(23122023L);
        frequency.setDayStartTime(23122023L);
        frequency.setOnboardingDate(23122023L);
        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
        holidayApplicables.add(holidayApplicable);
        frequency.setHolidayApplicable(holidayApplicables);
        frequency.setOnlyWorkdays(true);
        frequency.setOnlyWorkdays(true);
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
        frequency.setDayEndTime(23122023L);
        frequency.setDayStartTime(23122023L);
        frequency.setOnboardingDate(23122023L);
        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
        holidayApplicables.add(holidayApplicable);
        frequency.setHolidayApplicable(holidayApplicables);
        frequency.setOnlyWorkdays(true);
        frequency.setOnlyWorkdays(true);
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
        frequency.setDayEndTime(23122023L);
        frequency.setDayStartTime(23122023L);
        frequency.setOnboardingDate(23122023L);
        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
        holidayApplicables.add(holidayApplicable);
        frequency.setHolidayApplicable(holidayApplicables);
        frequency.setOnlyWorkdays(true);
        frequency.setOnlyWorkdays(true);
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
        HolidayApplicable holidayApplicable = new HolidayApplicable();
        holidayApplicable.setBizlogHolidays(true);
        holidayApplicable.setPublicHolidays(true);
        holidayApplicable.setClientHolidaays(false);
        Frequency frequency = new Frequency();
        frequency.setId(1L);
        frequency.setClient(client);
        frequency.setDayEndTime(23122023L);
        frequency.setDayStartTime(23122023L);
        frequency.setOnboardingDate(23122023L);
        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
        holidayApplicables.add(holidayApplicable);
        frequency.setHolidayApplicable(holidayApplicables);
        frequency.setOnlyWorkdays(true);
        frequency.setOnlyWorkdays(true);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume=in=(100,120)");
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
        frequency.setDayEndTime(23122023L);
        frequency.setDayStartTime(23122023L);
        frequency.setOnboardingDate(23122023L);
        List<HolidayApplicable> holidayApplicables = new ArrayList<>();
        holidayApplicables.add(holidayApplicable);
        frequency.setHolidayApplicable(holidayApplicables);
        frequency.setOnlyWorkdays(true);
        frequency.setOnlyWorkdays(true);
        frequency.setTicketsVolume("100");
        frequencyRepository.save(frequency);
        Node rootNode = new RSQLParser().parse("ticketsVolume=in=(100,120)");
        Specification<Frequency> spec = rootNode.accept(new CustomRsqlVisitor<Frequency>());
        List<Frequency> results = frequencyRepository.findAll(spec);

        assertThat(frequency, not(results));
    }

    // @Test
    // void should_create_new_frequency() throws Exception {
    //// ObjectMapper objectMapper = new ObjectMapper();
    //// objectMapper.registerModule(new JavaTimeModule());
    // int clientId = 1;
    // HolidayApplicable holidayApplicable = new HolidayApplicable();
    // holidayApplicable.setBizlogHolidays(true);
    // holidayApplicable.setPublicHolidays(true);
    // holidayApplicable.setClientHolidaays(false);
    //
    // Frequency frequency = new Frequency();
    //// frequency.setDayEndTime(LocalDateTime.of(2023, 10, 13, 0, 0));
    //// frequency.setDayStartTime(LocalDateTime.of(2023, 10, 13, 0, 0));
    //// frequency.setOnboardingDate(LocalDateTime.of(2023, 10, 13, 0, 0));
    //
    // List<HolidayApplicable> holidayApplicables = new ArrayList<>();
    // holidayApplicables.add(holidayApplicable);
    //
    // frequency.setHolidayApplicable(holidayApplicables);
    // frequency.setOnlyWorkdays(true);
    // frequency.setTicketsVolume("100");
    // this.mockMvc
    // .perform(post("/api/v1/{clientId}/frequency", clientId).contentType(MediaType.APPLICATION_JSON)
    // .content(new ObjectMapper().writeValueAsString(frequency)))
    // .andDo(print()).andExpect(status().is2xxSuccessful());
    // }
    //
    // @Test
    // void should_not_create_new_frequency() throws Exception {
    // int clientId = 11;
    // HolidayApplicable holidayApplicable = new HolidayApplicable();
    // holidayApplicable.setBizlogHolidays(true);
    // holidayApplicable.setPublicHolidays(true);
    // holidayApplicable.setClientHolidaays(false);
    // Frequency frequency = new Frequency();
    // frequency.setDayEndTime(LocalDateTime.of(2023, 10, 12, 0, 0));
    // frequency.setDayStartTime(LocalDateTime.of(2023, 10, 12, 0, 0));
    // frequency.setOnboardingDate(LocalDateTime.of(2023, 10, 12, 0, 0));
    // List<HolidayApplicable> holidayApplicables = new ArrayList<>();
    // holidayApplicables.add(holidayApplicable);
    // frequency.setHolidayApplicable(holidayApplicables);
    // frequency.setOnlyWorkdays(true);
    // frequency.setOnlyWorkdays(true);
    // frequency.setTicketsVolume("100");
    // this.mockMvc
    // .perform(post("/api/v1/{clientId}/frequency", clientId).contentType(MediaType.APPLICATION_JSON)
    // .content(new ObjectMapper().writeValueAsString(frequency)))
    // .andDo(print()).andExpect(status().isNotFound());
    // }
}
