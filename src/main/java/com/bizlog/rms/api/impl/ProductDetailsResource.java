package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ProductDetailsAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.ProductDetailsDTO;
import com.bizlog.rms.entities.product.ProductDetails;
import com.bizlog.rms.repository.BaseClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductDetailsResource extends BaseClientResource<ProductDetails, ProductDetailsDTO,ProductDetailsDTO> implements ProductDetailsAPI {
    public ProductDetailsResource(BaseClientRepository<ProductDetails, Long> baseClientRepository) {
        super(baseClientRepository);
    }
    @Override
    public ResponseEntity<ProductDetailsDTO> create(@PathVariable("clientId") Long clientId,
                                              @RequestBody ProductDetailsDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
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
