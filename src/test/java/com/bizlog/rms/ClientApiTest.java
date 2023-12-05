package com.bizlog.rms;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.utils.DataLoaderUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class ClientApiTest extends BaseApiTest {

    public static final String CLIENT_URL = "/api/v1/client";
    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getClients().forEach(clientRepository::save);
    }

    @AfterEach
    void afterEach() {
        clientRepository.deleteAll();
    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Client client = clientRepository.findAll().get(0);
        // String expected = toJson(client).orElse("");
        this.mockMvc.perform(get(CLIENT_URL + "/{id}", client.getId())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        this.mockMvc.perform(get(CLIENT_URL + "/{id}", 11)).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_client() throws Exception {
        Client client = new Client();
        client.setName("JOHN");
        client.setDescription("DEVELOPEMENT");
        client.setEmail("abc@gmail.com");
        client.setPhoneNumber("7698524598");
        client.setDomainName("abcdefghi");
        client.setActive(true);
        client.setType("abcde");
        this.mockMvc
                .perform(post(CLIENT_URL).contentType(MediaType.APPLICATION_JSON).content(toJson(client).orElse("")))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    // @Test
    // void should_not_create_new_client() throws Exception{
    // Client client = new Client();
    // client.setName("JOHN");
    // client.setDescription("DEVELOPEMENT");
    // client.setEmail("abc@gmail.com");
    // client.setPhoneNumber("7698524598");
    // client.setDomainName("abcdefghi");
    // client.setActive(true);
    // client.setType("abcde");
    // this.mockMvc
    // .perform(post(CLIENT_URL).contentType(MediaType.APPLICATION_JSON)
    // .content(toJson(client).orElse("")))
    // .andDo(print()).andExpect(status().isNotFound());
    // }

    /*
     * @Test void should_update_existing_client() throws Exception {
     * 
     * Client existingClient = new Client(); existingClient.setName("ExistingClient");
     * existingClient.setDescription("ExistingDescription"); existingClient.setEmail("existing@gmail.com");
     * existingClient.setPhoneNumber("1234567890"); existingClient.setDomainName("existingdomain");
     * existingClient.setActive(true); existingClient.setType("existingType"); existingClient =
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
