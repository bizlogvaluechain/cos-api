package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.BizlogTechEscalationAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.BizlogTechEscalationDTO;
import com.bizlog.rms.entities.Client;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
            Client client = getClientRepository().findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            BizlogTechEscalation entity = toEntity(inputDTO);
            entity.setClient(client);
            BizlogTechEscalation createdEntity = getBaseClientRepository().save(entity);
            BizlogTechEscalationDTO outPutDTO = toDTO(createdEntity);
            return outPutDTO;
        }).toList();
        return ResponseEntity.ok().body(outputDTOs);
    }

    @Override
    public ResponseEntity<BizlogTechEscalationDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<BizlogTechEscalationDTO>> getAll(Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<BizlogTechEscalationDTO>> search(Long clientId,
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
    protected BizlogTechEscalation toEntity(BizlogTechEscalationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected BizlogTechEscalationDTO toDTO(BizlogTechEscalation entity) {
        return getMapper().toDTO(entity);
    }

}
