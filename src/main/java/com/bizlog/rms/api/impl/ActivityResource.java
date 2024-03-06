package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ActivityAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.ActivitySOPDTO;
import com.bizlog.rms.entities.sop.ActivitySOP;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class ActivityResource extends BaseClientResource<ActivitySOP, ActivitySOPDTO, ActivitySOPDTO>
        implements ActivityAPI {
    public ActivityResource(BaseClientRepository<ActivitySOP, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<ActivitySOPDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ActivitySOPDTO>> getAll(@PathVariable Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected ActivitySOPDTO toDTO(ActivitySOP entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected ActivitySOP toEntity(ActivitySOPDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    public ResponseEntity<ActivitySOPDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid ActivitySOPDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<ActivitySOPDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody ActivitySOPDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<ActivitySOPDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(ActivitySOP.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(ActivitySOP.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(ActivitySOP.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(ActivitySOP.class,pageable,startDate,endDate,id);
    }

}
