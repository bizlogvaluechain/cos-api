package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ToolsAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.ToolsDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.sop.labourtoolvechile.Tools;
import com.bizlog.rms.exception.AlreadyExistException;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.utils.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ToolsResource extends BaseClientResource<Tools, ToolsDTO, ToolsDTO> implements ToolsAPI {
    public ToolsResource(BaseClientRepository<Tools, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    protected void preValidate(Long clientId, ToolsDTO payloadDTO, OperationType operationType) {
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
    public ResponseEntity<ToolsDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ToolsDTO>> getAll(Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected Tools toEntity(ToolsDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    public ResponseEntity<List<ToolsDTO>> create(@PathVariable("clientId") Long clientId,
            @RequestBody List<ToolsDTO> toolsDTO) {
        List<ToolsDTO> toolsDTOS = toolsDTO.stream().map(inputDTO -> {
            Organization organization = getOrganizationRepository().findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            Tools tools = toEntity(inputDTO);
            tools.setOrganization(organization);
            Tools createdTools = getBaseClientRepository().save(tools);
            ToolsDTO createdToolsDTO = toDTO(createdTools);
            return createdToolsDTO;
        }).toList();
        return ResponseEntity.ok().body(toolsDTOS);
    }

    @Override
    public ResponseEntity<ToolsDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody ToolsDTO toolsDTO) {
        return super.update(clientId, id, toolsDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected ToolsDTO toDTO(Tools entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    @Transactional
    public ResponseEntity<ToolsDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }
}
