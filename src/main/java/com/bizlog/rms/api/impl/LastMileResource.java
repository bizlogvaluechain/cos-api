package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.LastMileAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.activity.LastMileDTO;
import com.bizlog.rms.entities.activity.LastMile;
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
public class LastMileResource extends BaseClientResource<LastMile, LastMileDTO, LastMileDTO> implements LastMileAPI {
    public LastMileResource(BaseClientRepository<LastMile, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<LastMileDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<LastMileDTO>> getAll(Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected LastMileDTO toDTO(LastMile entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected LastMile toEntity(LastMileDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    public ResponseEntity<LastMileDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid LastMileDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<LastMileDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody LastMileDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<LastMileDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(LastMile.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(LastMile.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(LastMile.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(LastMile.class,pageable,startDate,endDate,id);
    }
}
