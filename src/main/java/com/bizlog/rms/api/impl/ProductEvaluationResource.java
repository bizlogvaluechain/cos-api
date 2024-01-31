package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ProductEvalutionsAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.ProductEvalutionsDTO;
import com.bizlog.rms.entities.product.ProductEvalutions;
import com.bizlog.rms.repository.BaseClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductEvaluationResource extends BaseClientResource<ProductEvalutions, ProductEvalutionsDTO,ProductEvalutionsDTO> implements ProductEvalutionsAPI {
    public ProductEvaluationResource(BaseClientRepository<ProductEvalutions, Long> baseClientRepository) {
        super(baseClientRepository);
    }
    @Override
    public ResponseEntity<ProductEvalutionsDTO> create(@PathVariable("clientId") Long clientId,
                                              @RequestBody ProductEvalutionsDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
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
