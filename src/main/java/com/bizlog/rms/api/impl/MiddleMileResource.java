package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.MiddleMileAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.activity.MiddleMileDTO;
import com.bizlog.rms.entities.activity.MiddleMile;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class MiddleMileResource extends BaseClientResource<MiddleMile, MiddleMileDTO, MiddleMileDTO>
        implements MiddleMileAPI {
    public MiddleMileResource(BaseClientRepository<MiddleMile, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<MiddleMileDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<MiddleMileDTO>> getAll(Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected MiddleMileDTO toDTO(MiddleMile entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected MiddleMile toEntity(MiddleMileDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    public ResponseEntity<MiddleMileDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid MiddleMileDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<MiddleMileDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody MiddleMileDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<MiddleMileDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(MiddleMile.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(MiddleMile.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(MiddleMile.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(MiddleMile.class,pageable,startDate,endDate,id);
    }
}
