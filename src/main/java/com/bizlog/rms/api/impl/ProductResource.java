package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ProductAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.ProductDTO;
import com.bizlog.rms.entities.sop.Product;
import com.bizlog.rms.exception.AlreadyExistException;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.utils.OperationType;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductResource extends BaseClientResource<Product, ProductDTO, ProductDTO> implements ProductAPI {
    public ProductResource(BaseClientRepository<Product, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<ProductDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid ProductDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }
    @Override
    protected void preValidate(Long clientId, ProductDTO payloadDTO, OperationType operationType) {
        super.preValidate(clientId, payloadDTO, operationType);
        if (operationType == OperationType.CREATE) {
            getBaseClientRepository()
                    .findByClient(getClientRepository().findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId)))
                    .ifPresent(X -> {
                        throw new AlreadyExistException(clientId);
                    });
        }
    }

    @Override
    public ResponseEntity<ProductDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody ProductDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<ProductDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ProductDTO>> getAll(@PathVariable("clientId") Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }
    @Override
    @Transactional
    public ResponseEntity<ProductDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    protected Product toEntity(ProductDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected ProductDTO toDTO(Product entity) {
        return getMapper().toDTO(entity);
    }
}
