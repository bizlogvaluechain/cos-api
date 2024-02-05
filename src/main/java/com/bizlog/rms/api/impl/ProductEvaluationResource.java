package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ProductEvalutionsAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.ProductEvalutionsDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.product.ProductEvalutions;
import com.bizlog.rms.entities.product.ProductInfo;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.repository.ProductEvalutionsRepository;
import com.bizlog.rms.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductEvaluationResource extends BaseClientResource<ProductEvalutions, ProductEvalutionsDTO,ProductEvalutionsDTO> implements ProductEvalutionsAPI {
    public ProductEvaluationResource(BaseClientRepository<ProductEvalutions, Long> baseClientRepository) {
        super(baseClientRepository);
    }
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductEvalutionsRepository productEvalutionsRepository;
    @Override
    public ResponseEntity<ProductEvalutionsDTO> create(@PathVariable("clientId") Long clientId,
                                              @RequestBody ProductEvalutionsDTO payloadDTO) {
        ProductInfo product = productInfoRepository.findById(payloadDTO.getProductInfoId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found", "id", clientId));
        Client client= clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        ProductEvalutions productEvalutions =toEntity(payloadDTO);
        productEvalutions.setClient(client);
        productEvalutions.setProductInfo(product);
        ProductEvalutions savedProductEvalutions = productEvalutionsRepository.save(productEvalutions);
        ProductEvalutionsDTO productEvalutionsDTO = toDTO(savedProductEvalutions);
        return new ResponseEntity<>(productEvalutionsDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductEvalutionsDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                              @RequestBody ProductEvalutionsDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<ProductEvalutionsDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ProductEvalutionsDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected ProductEvalutionsDTO toDTO(ProductEvalutions entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected ProductEvalutions toEntity(ProductEvalutionsDTO dto) {
        return getMapper().toEntity(dto);
    }
}
