package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.LocationSopAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.LocationSopDTO;
import com.bizlog.rms.entities.sop.LocationSop;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class LocationSopResource extends BaseClientResource<LocationSop, LocationSopDTO,LocationSopDTO> implements LocationSopAPI {
    public LocationSopResource(BaseClientRepository<LocationSop, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<LocationSopDTO> create(@PathVariable("clientId") Long clientId,
                                                  @RequestBody @Valid LocationSopDTO locationSopDTO) {
        locationSopDTO.setClientId(clientId);
        return super.create(clientId, locationSopDTO);
    }

    @Override
    public ResponseEntity<LocationSopDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                                  @RequestBody @Valid LocationSopDTO locationSopDTO) {
        return super.update(clientId, id, locationSopDTO);
    }

    @Override
    public ResponseEntity<LocationSopDTO> getById(@PathVariable("clientId") Long clientId,
                                                   @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<LocationSopDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                                Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected LocationSop toEntity(LocationSopDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected LocationSopDTO toDTO(LocationSop entity) {
        return getMapper().toDTO(entity);
    }
}
