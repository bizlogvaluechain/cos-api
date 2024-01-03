package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.EscalationMatrixAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.EscalationMatrixDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.escalationMatrix.EscalationMatrix;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@Slf4j
public class EscalationMatrixResource extends
        BaseClientResource<EscalationMatrix, EscalationMatrixDTO, EscalationMatrixDTO> implements EscalationMatrixAPI {
    public EscalationMatrixResource(BaseClientRepository<EscalationMatrix, Long> escalationMatrixRepository) {
        super(escalationMatrixRepository);
    }

    @Override
    public ResponseEntity<List<EscalationMatrixDTO>> create(@PathVariable("clientId") Long clientId,
                                                            @RequestBody @Valid List<EscalationMatrixDTO> escalationMatrixDTO) {
        List<EscalationMatrixDTO> outputDTOs = escalationMatrixDTO.stream()
                .map(inputDTO -> {
                    Client client = getClientRepository().findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
                    EscalationMatrix entity = toEntity(inputDTO);
                    entity.setClient(client);
                    EscalationMatrix createdEntity = getBaseClientRepository().save(entity);
                    return toDTO(createdEntity);
                })
                .toList();
        return ResponseEntity.ok().body(outputDTOs);
    }

    @Override
    public ResponseEntity<EscalationMatrixDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody @Valid EscalationMatrixDTO escalationMatrixDTO) {
        return super.update(clientId, id, escalationMatrixDTO);
    }

    @Override
    public ResponseEntity<PageResponse<EscalationMatrixDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Optional<Set<String>> attributesOpt, Pageable pageable) {
        return super.search(clientId, searchCriteria, pageable);
    }

    @Override
    public ResponseEntity<EscalationMatrixDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<EscalationMatrixDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected EscalationMatrix toEntity(EscalationMatrixDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected EscalationMatrixDTO toDTO(EscalationMatrix entity) {
        return getMapper().toDTO(entity);
    }
}
