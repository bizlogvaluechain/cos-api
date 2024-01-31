package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.LocationCenterAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.LocationCenterDTO;
import com.bizlog.rms.entities.product.LocationCenter;
import com.bizlog.rms.repository.BaseClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationCenterResource extends BaseClientResource<LocationCenter, LocationCenterDTO,LocationCenterDTO> implements LocationCenterAPI {
    public LocationCenterResource(BaseClientRepository<LocationCenter, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<LocationCenterDTO> create(@PathVariable("clientId") Long clientId,
                                              @RequestBody LocationCenterDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<LocationCenterDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                              @RequestBody LocationCenterDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<LocationCenterDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<LocationCenterDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected LocationCenterDTO toDTO(LocationCenter entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected LocationCenter toEntity(LocationCenterDTO dto) {
        return getMapper().toEntity(dto);
    }
}
