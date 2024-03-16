package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.BizlogOpsEscalationAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.BizlogOpsEscalationDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.escalationMatrix.BizlogOpsEscalation;
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
public class BizlogOpsEscalationResource
        extends BaseClientResource<BizlogOpsEscalation, BizlogOpsEscalationDTO, BizlogOpsEscalationDTO>
        implements BizlogOpsEscalationAPI {
    public BizlogOpsEscalationResource(BaseClientRepository<BizlogOpsEscalation, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<List<BizlogOpsEscalationDTO>> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid List<BizlogOpsEscalationDTO> inputDTOs) {
        List<BizlogOpsEscalationDTO> outputDTOs = inputDTOs.stream().map(inputDTO -> {
            Organization organization = getOrganizationRepository().findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            BizlogOpsEscalation entity = toEntity(inputDTO);
            entity.setOrganization(organization);
            BizlogOpsEscalation createdEntity = getBaseClientRepository().save(entity);
            BizlogOpsEscalationDTO outPutDTO = toDTO(createdEntity);
            return outPutDTO;
        }).toList();
        return ResponseEntity.ok().body(outputDTOs);
    }

    @Override
    public ResponseEntity<BizlogOpsEscalationDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<BizlogOpsEscalationDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<BizlogOpsEscalationDTO>> search(@PathVariable("clientId") Long clientId,
            Map<String, String> searchCriteria, Optional<Set<String>> attributesOpt, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<BizlogOpsEscalationDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody @Valid BizlogOpsEscalationDTO inputDTOs) {
        return super.update(clientId, id, inputDTOs);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<BizlogOpsEscalationDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    protected BizlogOpsEscalation toEntity(BizlogOpsEscalationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected BizlogOpsEscalationDTO toDTO(BizlogOpsEscalation entity) {
        return getMapper().toDTO(entity);
    }
    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(BizlogOpsEscalation.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(BizlogOpsEscalation.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(BizlogOpsEscalation.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(BizlogOpsEscalation.class,pageable,startDate,endDate,id);
    }

}
