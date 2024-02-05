package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ProductDetailsAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.ProductDetailsDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.product.ProductDetails;
import com.bizlog.rms.entities.product.ProductInfo;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.repository.ProductDetailsRepository;
import com.bizlog.rms.repository.ProductInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductDetailsResource extends BaseClientResource<ProductDetails, ProductDetailsDTO,ProductDetailsDTO> implements ProductDetailsAPI {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductDetailsRepository productDetailsRepository;
    public ProductDetailsResource(BaseClientRepository<ProductDetails, Long> baseClientRepository) {
        super(baseClientRepository);
    }
//    @Override
//    public ResponseEntity<ProductDetailsDTO> create(@PathVariable("clientId") Long clientId,
//                                              @RequestBody ProductDetailsDTO payloadDTO) {
//        ProductInfo product= productInfoRepository.findById(payloadDTO.getProductInfoId()).orElseThrow(() -> new ResourceNotFoundException("  product not found", "id", clientId));
//        log.info("productInfoRepository:{}",productInfoRepository);
//        log.info("product:{}",product.getId());
//        payloadDTO.set
//        payloadDTO.setClientId(clientId);
//        return super.create(clientId, payloadDTO);
//    }
        @Override
        public ResponseEntity<ProductDetailsDTO> create(@PathVariable("clientId") Long clientId,
                                                        @RequestBody ProductDetailsDTO payloadDTO) {
            ProductInfo product = productInfoRepository.findById(payloadDTO.getProductInfoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found", "id", clientId));
            Client client= clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            ProductDetails productDetails =toEntity(payloadDTO);
            productDetails.setClient(client);
            productDetails.setProductInfo(product);
            ProductDetails savedProductDetails = productDetailsRepository.save(productDetails);
            ProductDetailsDTO savedProductDetailsDTO = toDTO(savedProductDetails);
            return new ResponseEntity<>(savedProductDetailsDTO, HttpStatus.CREATED);
        }


    @Override
    public ResponseEntity<ProductDetailsDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                              @RequestBody ProductDetailsDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<ProductDetailsDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ProductDetailsDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected ProductDetailsDTO toDTO(ProductDetails entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected ProductDetails toEntity(ProductDetailsDTO dto) {
        return getMapper().toEntity(dto);
    }
}
