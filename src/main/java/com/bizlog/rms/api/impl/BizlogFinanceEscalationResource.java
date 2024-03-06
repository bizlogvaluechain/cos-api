package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.BizlogFinanceEscalationAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.BizlogFinanceEscalationDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.escalationMatrix.BizlogFinanceEscalation;
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
public class BizlogFinanceEscalationResource
        extends BaseClientResource<BizlogFinanceEscalation, BizlogFinanceEscalationDTO, BizlogFinanceEscalationDTO>
        implements BizlogFinanceEscalationAPI {
    public BizlogFinanceEscalationResource(BaseClientRepository<BizlogFinanceEscalation, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<List<BizlogFinanceEscalationDTO>> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid List<BizlogFinanceEscalationDTO> inputDTOs) {
        List<BizlogFinanceEscalationDTO> outputDTOs = inputDTOs.stream().map(inputDTO -> {
            Organization organization = getOrganizationRepository().findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            BizlogFinanceEscalation entity = toEntity(inputDTO);
            entity.setOrganization(organization);
            BizlogFinanceEscalation createdEntity = getBaseClientRepository().save(entity);
            BizlogFinanceEscalationDTO outPutDTO = toDTO(createdEntity);
            return outPutDTO;
        }).toList();
        return ResponseEntity.ok().body(outputDTOs);
    }

    @Override
    public ResponseEntity<BizlogFinanceEscalationDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<BizlogFinanceEscalationDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<BizlogFinanceEscalationDTO>> search(@PathVariable("clientId") Long clientId,
            Map<String, String> searchCriteria, Optional<Set<String>> attributesOpt, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<BizlogFinanceEscalationDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody @Valid BizlogFinanceEscalationDTO inputDTOs) {
        return super.update(clientId, id, inputDTOs);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<BizlogFinanceEscalationDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    protected BizlogFinanceEscalation toEntity(BizlogFinanceEscalationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected BizlogFinanceEscalationDTO toDTO(BizlogFinanceEscalation entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(BizlogFinanceEscalation.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(BizlogFinanceEscalation.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(BizlogFinanceEscalation.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(BizlogFinanceEscalation.class,pageable,startDate,endDate,id);
    }

}
