package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.RegionSpecificLocationAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.locationService.RegionSpecificLocationDTO;
import com.bizlog.rms.entities.location.RegionSpecificLocation;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegionSpecificLocationResource
        extends BaseClientResource<RegionSpecificLocation, RegionSpecificLocationDTO, RegionSpecificLocationDTO>
        implements RegionSpecificLocationAPI {
    public RegionSpecificLocationResource(BaseClientRepository<RegionSpecificLocation, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<RegionSpecificLocationDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid RegionSpecificLocationDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<RegionSpecificLocationDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody RegionSpecificLocationDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<RegionSpecificLocationDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<RegionSpecificLocationDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<RegionSpecificLocationDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    protected RegionSpecificLocation toEntity(RegionSpecificLocationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected RegionSpecificLocationDTO toDTO(RegionSpecificLocation entity) {
        return getMapper().toDTO(entity);
    }
}
