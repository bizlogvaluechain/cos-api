package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.FirstMileAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.activity.FirstMileDTO;
import com.bizlog.rms.entities.activity.FirstMile;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstMileResource extends BaseClientResource<FirstMile, FirstMileDTO,FirstMileDTO> implements FirstMileAPI {
    public FirstMileResource(BaseClientRepository<FirstMile, Long> baseClientRepository) {
        super(baseClientRepository);
    }
    @Override
    public ResponseEntity<FirstMileDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<FirstMileDTO>> getAll(Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected FirstMileDTO toDTO(FirstMile entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected FirstMile toEntity(FirstMileDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    public ResponseEntity<FirstMileDTO> create(@PathVariable("clientId") Long clientId,
                                            @RequestBody @Valid FirstMileDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<FirstMileDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                            @RequestBody FirstMileDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }
}
