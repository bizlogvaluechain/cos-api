package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientFinanceEscalationAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.ClientFinanceEscalationDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.escalationMatrix.ClientFinanceEscalation;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ClientFinanceEscalationResource
        extends BaseClientResource<ClientFinanceEscalation, ClientFinanceEscalationDTO, ClientFinanceEscalationDTO>
        implements ClientFinanceEscalationAPI {
    public ClientFinanceEscalationResource(BaseClientRepository<ClientFinanceEscalation, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<List<ClientFinanceEscalationDTO>> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid List<ClientFinanceEscalationDTO> inputDTOs) {
        List<ClientFinanceEscalationDTO> outputDTOs = inputDTOs.stream().map(inputDTO -> {
            Organization organization = getOrganizationRepository().findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            ClientFinanceEscalation entity = toEntity(inputDTO);
            entity.setOrganization(organization);
            ClientFinanceEscalation createdEntity = getBaseClientRepository().save(entity);
            ClientFinanceEscalationDTO outPutDTO = toDTO(createdEntity);
            return outPutDTO;
        }).toList();
        return ResponseEntity.ok().body(outputDTOs);
    }

    @Override

    public ResponseEntity<ClientFinanceEscalationDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {

        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ClientFinanceEscalationDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<ClientFinanceEscalationDTO>> search(@PathVariable("clientId") Long clientId,
            Map<String, String> searchCriteria, Optional<Set<String>> attributesOpt, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<ClientFinanceEscalationDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody @Valid ClientFinanceEscalationDTO inputDTOs) {
        return super.update(clientId, id, inputDTOs);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<ClientFinanceEscalationDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    protected ClientFinanceEscalation toEntity(ClientFinanceEscalationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected ClientFinanceEscalationDTO toDTO(ClientFinanceEscalation entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(ClientFinanceEscalation.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(ClientFinanceEscalation.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(ClientFinanceEscalation.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(ClientFinanceEscalation.class,pageable,startDate,endDate,id);
    }


}
