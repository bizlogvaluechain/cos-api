package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.BizlogTechEscalationAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.BizlogTechEscalationDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.escalationMatrix.BizlogTechEscalation;
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
public class BizlogTechEscalationResource
        extends BaseClientResource<BizlogTechEscalation, BizlogTechEscalationDTO, BizlogTechEscalationDTO>
        implements BizlogTechEscalationAPI {
    public BizlogTechEscalationResource(BaseClientRepository<BizlogTechEscalation, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<List<BizlogTechEscalationDTO>> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid List<BizlogTechEscalationDTO> inputDTOs) {
        List<BizlogTechEscalationDTO> outputDTOs = inputDTOs.stream().map(inputDTO -> {
            Organization organization = getOrganizationRepository().findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            BizlogTechEscalation entity = toEntity(inputDTO);
            entity.setOrganization(organization);
            BizlogTechEscalation createdEntity = getBaseClientRepository().save(entity);
            BizlogTechEscalationDTO outPutDTO = toDTO(createdEntity);
            return outPutDTO;
        }).toList();
        return ResponseEntity.ok().body(outputDTOs);
    }

    @Override
    public ResponseEntity<BizlogTechEscalationDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<BizlogTechEscalationDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<BizlogTechEscalationDTO>> search(@PathVariable("clientId") Long clientId,
            Map<String, String> searchCriteria, Optional<Set<String>> attributesOpt, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<BizlogTechEscalationDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody @Valid BizlogTechEscalationDTO inputDTOs) {
        return super.update(clientId, id, inputDTOs);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<BizlogTechEscalationDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    protected BizlogTechEscalation toEntity(BizlogTechEscalationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected BizlogTechEscalationDTO toDTO(BizlogTechEscalation entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(BizlogTechEscalation.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(BizlogTechEscalation.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(BizlogTechEscalation.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(BizlogTechEscalation.class,pageable,startDate,endDate,id);
    }

}
