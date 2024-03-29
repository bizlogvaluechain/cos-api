package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.LabourAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.LabourDTO;
import com.bizlog.rms.entities.sop.labourtoolvechile.Labour;
import com.bizlog.rms.exception.AlreadyExistException;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.utils.OperationType;
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
public class LabourResource extends BaseClientResource<Labour, LabourDTO, LabourDTO> implements LabourAPI {
    public LabourResource(BaseClientRepository<Labour, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    protected void preValidate(Long clientId, LabourDTO payloadDTO, OperationType operationType) {
        super.preValidate(clientId, payloadDTO, operationType);
        if (operationType == OperationType.CREATE) {
            getBaseClientRepository()
                    .findByOrganization(getOrganizationRepository().findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId)))
                    .ifPresent(X -> {
                        throw new AlreadyExistException(clientId);
                    });
        }
    }

    @Override
    public ResponseEntity<LabourDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<LabourDTO>> getAll(Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected LabourDTO toDTO(Labour entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected Labour toEntity(LabourDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    public ResponseEntity<LabourDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid LabourDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<LabourDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody LabourDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<LabourDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(Labour.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(Labour.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(Labour.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(Labour.class,pageable,startDate,endDate,id);
    }

}
