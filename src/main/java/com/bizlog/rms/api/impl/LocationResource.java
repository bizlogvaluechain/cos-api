package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.LocationAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.locationService.LocationDTO;
import com.bizlog.rms.entities.location.Location;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class LocationResource extends BaseClientResource<Location, LocationDTO, LocationDTO> implements LocationAPI {

    public LocationResource(BaseClientRepository<Location, Long> locationRepository) {
        super(locationRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<LocationDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid LocationDTO locationDTO) {
        locationDTO.setClientId(clientId);
        return super.create(clientId, locationDTO);
    }

    @Override
    public ResponseEntity<LocationDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody @Valid LocationDTO locationDTO) {
        return super.update(clientId, id, locationDTO);
    }

    @Override
    public ResponseEntity<LocationDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<LocationDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<LocationDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Pageable pageable) {
        return super.search(clientId, searchCriteria, pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }
    @Override
    @Transactional
    public ResponseEntity<LocationDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    protected Location toEntity(LocationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected LocationDTO toDTO(Location entity) {
        return getMapper().toDTO(entity);
    }
}
