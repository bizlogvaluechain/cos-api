package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.OperationContactAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.OperationContactInformationDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.clientinfo.contactinformation.OperationContactInformation;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
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
public class OperationContactResource extends
        BaseClientResource<OperationContactInformation, OperationContactInformationDTO, OperationContactInformationDTO>
        implements OperationContactAPI {
    public OperationContactResource(BaseClientRepository<OperationContactInformation, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<List<OperationContactInformationDTO>> create(@PathVariable("clientId") Long clientId,
            @RequestBody List<OperationContactInformationDTO> operationContactInformationDTOS) {
        List<OperationContactInformationDTO> operationContactInformationDTOList = operationContactInformationDTOS
                .stream().map(operationContactInformationDTO -> {
                    Organization organization = getOrganizationRepository().findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
                    OperationContactInformation operationContactInformation = toEntity(operationContactInformationDTO);
                    operationContactInformation.setOrganization(organization);
                    OperationContactInformation operationContactInformation1 = getBaseClientRepository()
                            .save(operationContactInformation);
                    OperationContactInformationDTO operationContactInformationDTO1 = toDTO(
                            operationContactInformation1);
                    return operationContactInformationDTO1;
                }).toList();
        return ResponseEntity.ok().body(operationContactInformationDTOList);
    }

    @Override
    public ResponseEntity<OperationContactInformationDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<OperationContactInformationDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        log.info("client id----------->" + clientId.toString());
        log.info("pagination----------->" + pageable.toString());
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected OperationContactInformation toEntity(OperationContactInformationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected OperationContactInformationDTO toDTO(OperationContactInformation entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    public ResponseEntity<OperationContactInformationDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody OperationContactInformationDTO operationContactInformationDTO) {
        return super.update(clientId, id, operationContactInformationDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<OperationContactInformationDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }


    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(OperationContactInformation.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(OperationContactInformation.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(OperationContactInformation.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(OperationContactInformation.class,pageable,startDate,endDate,id);
    }
}
