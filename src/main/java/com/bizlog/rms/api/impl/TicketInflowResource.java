package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.TicketInflowAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.TicketInflowDTO;
import com.bizlog.rms.repository.BaseClientRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
public class TicketInflowResource extends
        BaseClientResource<com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow, TicketInflowDTO, TicketInflowDTO>
        implements TicketInflowAPI {

    public TicketInflowResource(
            BaseClientRepository<com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow, Long> ticketCreationConfigRepository) {
        super(ticketCreationConfigRepository);
    }

    @Override
    public ResponseEntity<TicketInflowDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<TicketInflowDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<TicketInflowDTO>> search(@PathVariable Long clientId,
            @RequestParam Map<String, String> searchCriteria, Pageable pageable) {
        return super.search(clientId, searchCriteria, pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<TicketInflowDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid TicketInflowDTO ticketCreationConfigDTO) {
        ticketCreationConfigDTO.setClientId(clientId);
        return super.create(clientId, ticketCreationConfigDTO);
    }

    @Override
    public ResponseEntity<TicketInflowDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody @Valid TicketInflowDTO ticketCreationConfigDTO) {
        return super.update(clientId, id, ticketCreationConfigDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected TicketInflowDTO toDTO(com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow toEntity(TicketInflowDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    @Transactional
    public ResponseEntity<TicketInflowDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow.class,pageable,startDate,endDate,id);
    }
}
