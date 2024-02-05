package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ProductInfoAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.ProductInfoDTO;
import com.bizlog.rms.entities.product.ProductInfo;
import com.bizlog.rms.repository.BaseClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductInfoResource extends BaseClientResource<ProductInfo, ProductInfoDTO,ProductInfoDTO> implements ProductInfoAPI {
    public ProductInfoResource(BaseClientRepository<ProductInfo, Long> baseClientRepository) {
        super(baseClientRepository);
    }
    @Override
    public ResponseEntity<ProductInfoDTO> create(@PathVariable("clientId") Long clientId,
                                              @RequestBody ProductInfoDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<ProductInfoDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                              @RequestBody ProductInfoDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<ProductInfoDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ProductInfoDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected ProductInfoDTO toDTO(ProductInfo entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected ProductInfo toEntity(ProductInfoDTO dto) {
        return getMapper().toEntity(dto);
    }
}
