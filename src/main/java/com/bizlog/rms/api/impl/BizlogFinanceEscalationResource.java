package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.BizlogFinanceEscalationAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.BizlogFinanceEscalationDTO;
import com.bizlog.rms.entities.Client;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
public class BizlogFinanceEscalationResource
        extends BaseClientResource<BizlogFinanceEscalation, BizlogFinanceEscalationDTO, BizlogFinanceEscalationDTO>
        implements BizlogFinanceEscalationAPI {
    public BizlogFinanceEscalationResource(BaseClientRepository baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<List<BizlogFinanceEscalationDTO>> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid List<BizlogFinanceEscalationDTO> inputDTOs) {
        List<BizlogFinanceEscalationDTO> outputDTOs = inputDTOs.stream().map(inputDTO -> {
            Client client = getClientRepository().findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            BizlogFinanceEscalation entity = toEntity(inputDTO);
            entity.setClient(client);
            BizlogFinanceEscalation createdEntity = getBaseClientRepository().save(entity);
            BizlogFinanceEscalationDTO outPutDTO = toDTO(createdEntity);
            return outPutDTO;
        }).toList();
        return ResponseEntity.ok().body(outputDTOs);
    }

    @Override
    public ResponseEntity<BizlogFinanceEscalationDTO> getById(@PathVariable("clientId")Long clientId,@PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<BizlogFinanceEscalationDTO>> getAll(@PathVariable("clientId")Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<BizlogFinanceEscalationDTO>> search(@PathVariable("clientId")Long clientId,
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
    protected BizlogFinanceEscalation toEntity(BizlogFinanceEscalationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected BizlogFinanceEscalationDTO toDTO(BizlogFinanceEscalation entity) {
        return getMapper().toDTO(entity);
    }
}
