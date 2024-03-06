package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientTechEscalationAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.ClientTechEscalationDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.escalationMatrix.ClientTechEscalation;
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
public class ClientTechEscalationResource
        extends BaseClientResource<ClientTechEscalation, ClientTechEscalationDTO, ClientTechEscalationDTO>
        implements ClientTechEscalationAPI {
    public ClientTechEscalationResource(BaseClientRepository<ClientTechEscalation, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<List<ClientTechEscalationDTO>> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid List<ClientTechEscalationDTO> inputDTOs) {
        List<ClientTechEscalationDTO> outputDTOs = inputDTOs.stream().map(inputDTO -> {
            Organization organization = getOrganizationRepository().findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            ClientTechEscalation entity = toEntity(inputDTO);
            entity.setOrganization(organization);
            ClientTechEscalation createdEntity = getBaseClientRepository().save(entity);
            ClientTechEscalationDTO outPutDTO = toDTO(createdEntity);
            return outPutDTO;
        }).toList();
        return ResponseEntity.ok().body(outputDTOs);
    }

    @Override
    public ResponseEntity<ClientTechEscalationDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {

        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ClientTechEscalationDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<ClientTechEscalationDTO>> search(@PathVariable("clientId") Long clientId,
            Map<String, String> searchCriteria, Optional<Set<String>> attributesOpt, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<ClientTechEscalationDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody @Valid ClientTechEscalationDTO inputDTOs) {
        return super.update(clientId, id, inputDTOs);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected ClientTechEscalation toEntity(ClientTechEscalationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected ClientTechEscalationDTO toDTO(ClientTechEscalation entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    @Transactional
    public ResponseEntity<ClientTechEscalationDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(ClientTechEscalation.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(ClientTechEscalation.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(ClientTechEscalation.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(ClientTechEscalation.class,pageable,startDate,endDate,id);
    }
}
