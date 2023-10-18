package com.bizlog.rms;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.productInformation.ProductInformation;
import com.bizlog.rms.entities.productInformation.ProductSize;
import com.bizlog.rms.repository.ProductInformatiomRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductionInformationApiIT extends BaseApiTest {
    @Autowired
    private ProductInformatiomRepository productInformatiomRepository;

    @BeforeEach
    void beforeEach() {
        Client client = DataLoaderUtil.getClient();
        client = clientRepository.save(client);
        DataLoaderUtil.getProductInformation(client).forEach(productInformatiomRepository::save);
    }

    @AfterEach
    void afterEach() {

        productInformatiomRepository.deleteAll();
        clientRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        int clientId = 1;
        int id = 1;
        this.mockMvc.perform(get("/api/v1/{clientId}/product-information/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/{clientId}/product-information/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_productInformation() throws Exception {
        int clientId = 1;
        ProductSize productSize = new ProductSize();
        productSize.setLarge("yes");
        productSize.setMini("no");
        productSize.setMedium("yes");
        productSize.setXLarge("no");
        productSize.setSmall("no");

        ProductInformation productInformation = new ProductInformation();
        productInformation.setProductCategory("electronics");

        List<ProductSize> productSizes = new ArrayList<>();
        productSizes.add(productSize);

        productInformation.setProductSize(productSizes);
        productInformation.setIsProductInformationRequiredForTicketCreation(false);
        productInformation.setProductSubCategory("mobiles");
        productInformation.setIsInventoryNeeded(false);
        productInformation.setIsPackingNeeded(true);
        productInformation.setIsVehicleNeeded(true);
        productInformation.setIsWareHousingNeeded(true);
        this.mockMvc
                .perform(
                        post("/api/v1/{clientId}/product-information", clientId).contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(productInformation)))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void should_not_create_new_productInformation() throws Exception {
        int clientId = 11;
        ProductSize productSize = new ProductSize();
        productSize.setLarge("yes");
        productSize.setMini("no");
        productSize.setMedium("yes");
        productSize.setXLarge("no");
        productSize.setSmall("no");

        ProductInformation productInformation = new ProductInformation();
        productInformation.setProductCategory("electronics");

        List<ProductSize> productSizes = new ArrayList<>();
        productSizes.add(productSize);

        productInformation.setProductSize(productSizes);
        productInformation.setIsProductInformationRequiredForTicketCreation(false);
        productInformation.setProductSubCategory("mobiles");
        productInformation.setIsInventoryNeeded(false);
        productInformation.setIsPackingNeeded(true);
        productInformation.setIsVehicleNeeded(true);
        productInformation.setIsWareHousingNeeded(true);
        this.mockMvc
                .perform(
                        post("/api/v1/{clientId}/product-information", clientId).contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(productInformation)))
                .andDo(print()).andExpect(status().isNotFound());
    }
}
