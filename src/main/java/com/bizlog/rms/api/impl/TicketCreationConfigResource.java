package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.TicketCreationConfigAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.TicketCreationConfigDTO;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationConfig;
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
public class TicketCreationConfigResource
        extends BaseClientResource<TicketCreationConfig, TicketCreationConfigDTO, TicketCreationConfigDTO>
        implements TicketCreationConfigAPI {

    public TicketCreationConfigResource(
            BaseClientRepository<TicketCreationConfig, Long> ticketCreationConfigRepository) {
        super(ticketCreationConfigRepository);
    }

    @Override
    public ResponseEntity<TicketCreationConfigDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<TicketCreationConfigDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<TicketCreationConfigDTO>> search(@PathVariable Long clientId,
            @RequestParam Map<String, String> searchCriteria, Pageable pageable) {
        return super.search(clientId, searchCriteria, pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<TicketCreationConfigDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid TicketCreationConfigDTO ticketCreationConfigDTO) {
        ticketCreationConfigDTO.setClientId(clientId);
        return super.create(clientId, ticketCreationConfigDTO);
    }

    @Override
    public ResponseEntity<TicketCreationConfigDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody TicketCreationConfigDTO ticketCreationConfigDTO) {
        return super.update(clientId, id, ticketCreationConfigDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected TicketCreationConfigDTO toDTO(TicketCreationConfig entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected TicketCreationConfig toEntity(TicketCreationConfigDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    public ResponseEntity<TicketCreationConfigDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }
}
