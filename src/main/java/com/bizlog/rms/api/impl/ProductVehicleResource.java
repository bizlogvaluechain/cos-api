package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ProductVehicleAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.ProductVehicleDTO;
import com.bizlog.rms.entities.product.ProductVehicle;
import com.bizlog.rms.repository.BaseClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductVehicleResource extends BaseClientResource<ProductVehicle, ProductVehicleDTO,ProductVehicleDTO> implements ProductVehicleAPI {
    public ProductVehicleResource(BaseClientRepository<ProductVehicle, Long> baseClientRepository) {
        super(baseClientRepository);
    }
    @Override
    public ResponseEntity<ProductVehicleDTO> create(@PathVariable("clientId") Long clientId,
                                              @RequestBody ProductVehicleDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<ProductVehicleDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                              @RequestBody ProductVehicleDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<ProductVehicleDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ProductVehicleDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected ProductVehicleDTO toDTO(ProductVehicle entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected ProductVehicle toEntity(ProductVehicleDTO dto) {
        return getMapper().toEntity(dto);
    }
}
