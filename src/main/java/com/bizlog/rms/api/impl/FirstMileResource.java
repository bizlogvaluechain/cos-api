package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.FirstMileAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.activity.FirstMileDTO;
import com.bizlog.rms.entities.activity.FirstMile;
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
public class FirstMileResource extends BaseClientResource<FirstMile, FirstMileDTO, FirstMileDTO>
        implements FirstMileAPI {
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

    @Override
    @Transactional
    public ResponseEntity<FirstMileDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(FirstMile.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(FirstMile.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(FirstMile.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(FirstMile.class,pageable,startDate,endDate,id);
    }
}
