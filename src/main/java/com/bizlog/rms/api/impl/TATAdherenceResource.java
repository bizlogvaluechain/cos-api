package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.TATAdherenceAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.TATAdherenceDTO;
import com.bizlog.rms.entities.sop.TATAdherence;
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
import java.util.Map;

@Slf4j
@RestController

public class TATAdherenceResource extends BaseClientResource<TATAdherence, TATAdherenceDTO, TATAdherenceDTO>
        implements TATAdherenceAPI {

    public TATAdherenceResource(BaseClientRepository<TATAdherence, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<TATAdherenceDTO> create(@PathVariable("clientId") Long clientId,
            @Valid TATAdherenceDTO tatActivityDTO) {
        return super.create(clientId, tatActivityDTO);
    }

    @Override
    public ResponseEntity<TATAdherenceDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<TATAdherenceDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<TATAdherenceDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Pageable pageable) {
        return super.search(clientId, searchCriteria, pageable);
    }

    @Override
    public ResponseEntity<TATAdherenceDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody @Valid TATAdherenceDTO tatActivityDTO) {
        return super.update(clientId, id, tatActivityDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<TATAdherenceDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    protected TATAdherence toEntity(TATAdherenceDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected TATAdherenceDTO toDTO(TATAdherence entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(TATAdherence.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(TATAdherence.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(TATAdherence.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(TATAdherence.class,pageable,startDate,endDate,id);
    }
}
