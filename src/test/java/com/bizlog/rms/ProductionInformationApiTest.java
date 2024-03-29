package com.bizlog.rms;

import com.bizlog.rms.dto.productInformation.ProductInformationDTO;
import com.bizlog.rms.entities.Organization;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class ProductionInformationApiTest extends BaseApiTest {
    @Autowired
    private ProductInformatiomRepository productInformatiomRepository;

    @BeforeEach
    void beforeEach() {
        super.beforeEach();
        DataLoaderUtil.getProductInformation(getOrganization()).forEach(productInformatiomRepository::save);
    }

    @AfterEach
    void afterEach() {

        productInformatiomRepository.deleteAll();
        organizationRepository.deleteAll();

    }

    @Test
    void should_retrieve_with_valid_user_id() throws Exception {
        Long clientId = productInformatiomRepository.findAll().get(0).getclientId();
        Long id = productInformatiomRepository.findAll().get(0).getId();
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/product-information/{id}", clientId, id)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_not_retrieve_with_invalid_user_id() throws Exception {
        int clientId = 11;
        int id = 11;
        this.mockMvc.perform(get("/api/v1/cos/{clientId}/product-information/{id}", clientId, id)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_create_new_productInformation() throws Exception {
        Organization organization = getOrganization();
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
                .perform(post("/api/v1/cos/{clientId}/product-information", organization.getId())
                        .contentType(MediaType.APPLICATION_JSON)
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
                .perform(post("/api/v1/cos/{clientId}/product-information", clientId)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(productInformation).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void should_update_existing_productInformation() throws Exception {
        ProductSize productSize = new ProductSize();
        productSize.setLarge("yes");
        productSize.setMini("no");
        productSize.setMedium("yes");
        productSize.setXLarge("no");
        productSize.setSmall("no");

        ProductInformation initialProductInformation = new ProductInformation();
        initialProductInformation.setProductCategory("electronics");

        List<ProductSize> productSizes = new ArrayList<>();
        productSizes.add(productSize);

        initialProductInformation.setProductSize(productSizes);
        initialProductInformation.setIsProductInformationRequiredForTicketCreation(false);
        initialProductInformation.setProductSubCategory("mobiles");
        initialProductInformation.setIsInventoryNeeded(false);
        initialProductInformation.setIsPackingNeeded(true);
        initialProductInformation.setIsVehicleNeeded(true);
        initialProductInformation.setIsWareHousingNeeded(true);
        Organization organization = getOrganization();
        initialProductInformation.setOrganization(organization);
        initialProductInformation = productInformatiomRepository.save(initialProductInformation);

        ProductInformationDTO updateProductInformation = getMapper().toDTO(initialProductInformation);
        updateProductInformation.setProductCategory("textiles");
        updateProductInformation.setIsProductInformationRequiredForTicketCreation(true);
        updateProductInformation.setProductSubCategory("shirt");
        updateProductInformation.setIsInventoryNeeded(true);
        updateProductInformation.setIsPackingNeeded(false);
        updateProductInformation.setIsVehicleNeeded(true);

        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/product-information/{id}", organization.getId(),
                        initialProductInformation.getId()).contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(updateProductInformation).orElse("")))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(toJson(updateProductInformation).orElse("")));
    }

    @Test
    void should_not_update_existing_productInformation() throws Exception {
        int clientId = 11;
        long id = 299;

        ProductInformation updateProductInformation = new ProductInformation();
        updateProductInformation.setProductCategory("textiles");
        updateProductInformation.setIsProductInformationRequiredForTicketCreation(true);
        updateProductInformation.setProductSubCategory("shirt");
        updateProductInformation.setIsInventoryNeeded(true);
        updateProductInformation.setIsPackingNeeded(false);
        updateProductInformation.setIsVehicleNeeded(true);

        this.mockMvc
                .perform(put("/api/v1/cos/{clientId}/product-information/{id}", clientId, id)
                        .contentType(MediaType.APPLICATION_JSON).content(toJson(updateProductInformation).orElse("")))
                .andDo(print()).andExpect(status().isNotFound());

    }

    @Test
    void should_delete_existing_productInformation() throws Exception {

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
        Organization organization = getOrganization();
        productInformation.setOrganization(organization);
        productInformation = productInformatiomRepository.save(productInformation);
        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/product-information/{id}", organization.getId(),
                productInformation.getId())).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void should_not_delete_nonexistent_product_information() throws Exception {
        int clientId = 11;
        int nonexistentId = 999;
        this.mockMvc.perform(delete("/api/v1/cos/{clientId}/product-information/{id}", clientId, nonexistentId))
                .andDo(print()).andExpect(status().isNotFound());
    }

}
