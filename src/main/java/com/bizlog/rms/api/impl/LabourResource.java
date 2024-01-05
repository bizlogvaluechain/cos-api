package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.LabourAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.LabourDTO;
import com.bizlog.rms.entities.sop.labourtoolvechile.Labour;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabourResource extends BaseClientResource<Labour, LabourDTO, LabourDTO> implements LabourAPI {
    public LabourResource(BaseClientRepository<Labour, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<LabourDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<LabourDTO>> getAll(Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected LabourDTO toDTO(Labour entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected Labour toEntity(LabourDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    public ResponseEntity<LabourDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid LabourDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<LabourDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody LabourDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

}
