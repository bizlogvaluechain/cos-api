package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientOpsEscalationAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.ClientOpsEscalationDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.escalationMatrix.ClientOpsEscalation;
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
public class ClientOpsEscalationResource
        extends BaseClientResource<ClientOpsEscalation, ClientOpsEscalationDTO, ClientOpsEscalationDTO>
        implements ClientOpsEscalationAPI {
    public ClientOpsEscalationResource(BaseClientRepository<ClientOpsEscalation, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<List<ClientOpsEscalationDTO>> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid List<ClientOpsEscalationDTO> inputDTOs) {
        List<ClientOpsEscalationDTO> outputDTOs = inputDTOs.stream().map(inputDTO -> {
            Client client = getClientRepository().findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            ClientOpsEscalation entity = toEntity(inputDTO);
            entity.setClient(client);
            ClientOpsEscalation createdEntity = getBaseClientRepository().save(entity);
            ClientOpsEscalationDTO outPutDTO = toDTO(createdEntity);
            return outPutDTO;
        }).toList();
        return ResponseEntity.ok().body(outputDTOs);
    }

    @Override
    public ResponseEntity<ClientOpsEscalationDTO> getById(@PathVariable("clientId")Long clientId,@PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ClientOpsEscalationDTO>> getAll(@PathVariable("clientId")Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<ClientOpsEscalationDTO>> search(@PathVariable("clientId")Long clientId,
            Map<String, String> searchCriteria, Optional<Set<String>> attributesOpt, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<ClientOpsEscalationDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody @Valid ClientOpsEscalationDTO inputDTOs) {
        return super.update(clientId, id, inputDTOs);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected ClientOpsEscalation toEntity(ClientOpsEscalationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected ClientOpsEscalationDTO toDTO(ClientOpsEscalation entity) {
        return getMapper().toDTO(entity);
    }

}
