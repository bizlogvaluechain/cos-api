package com.bizlog.rms;

import com.bizlog.rms.entities.Organization;

import com.bizlog.rms.repository.OrganizationRepository;
import com.bizlog.rms.utils.DataLoaderUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = COSApiApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClientApiTest extends BaseApiTest {

    public static final String CLIENT_URL = "/api/v1/cos/organization";
    @Autowired
    private OrganizationRepository organizationRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getClients().forEach(organizationRepository::save);
    }

    @AfterEach
    void afterEach() {
        organizationRepository.deleteAll();
    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Organization organization = organizationRepository.findAll().get(0);
        // String expected = toJson(client).orElse("");
        this.mockMvc.perform(get(CLIENT_URL + "/{id}", organization.getId())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        this.mockMvc.perform(get(CLIENT_URL + "/{id}", 134561)).andDo(print()).andExpect(status().isNotFound());

    }

//    @Test
//    void should_create_new_root() throws Exception {
//        Organization organization = new Organization();
//        organization.setName("JOHN");
//        organization.setDescription("DEVELOPEMENT");
//        organization.setEmail("abc@gmail.com");
//        organization.setPhoneNumber("7698524598");
//        organization.setDomainName("abcdefghi");
//        organization.setActive(true);
//        organization.setOrganizationType(OrganizationType.ROOT);
//        organization.setDateOfOnboarding(27122023L);
//        this.mockMvc.perform(
//                post(CLIENT_URL).contentType(MediaType.APPLICATION_JSON).content(toJson(organization).orElse("")))
//                .andDo(print()).andExpect(status().is2xxSuccessful());
//    }

    /*
     * @Test void should_update_existing_client() throws Exception {
     * 
     * Client existingClient = new Client(); existingClient.setName("ExistingClient");
     * existingClient.setDescription("ExistingDescription"); existingClient.setEmail("existing@gmail.com");
     * existingClient.setPhoneNumber("1234567890"); existingClient.setDomainName("existingdomain");
     * existingClient.setActive(true); existingClient.setOrganizationType("existingType"); existingClient =
     * clientRepository.save(existingClient);
     * 
     * existingClient.setActive(false);
     * 
     * ClientDTO dto = getMapper().toDTO(existingClient); dto.setActive(false);
     * 
     * this.mockMvc .perform(put(CLIENT_URL + "/{id}", existingClient.getId()) .contentType(MediaType.APPLICATION_JSON)
     * .content(toJson(dto).orElse(""))) .andDo(print()) .andExpect(status().isOk());
     * 
     * 
     * Client updatedEntity = clientRepository.findById(existingClient.getId()).orElse(null);
     * assertNotNull(updatedEntity); // assertEquals("UpdatedName", updatedEntity.getName()); //
     * assertEquals("UpdatedDescription", updatedEntity.getDescription()); // assertEquals("updated@gmail.com",
     * updatedEntity.getEmail()); // assertEquals("9876543210", updatedEntity.getPhoneNumber()); //
     * assertEquals("updateddomain", updatedEntity.getDomainName()); assertEquals(false, updatedEntity.getActive());
     * //assertEquals("updatedType", updatedEntity.getType()); }
     */

}
