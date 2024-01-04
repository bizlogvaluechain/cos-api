package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientTechEscalationAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.ClientTechEscalationDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.escalationMatrix.ClientTechEscalation;
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
public class ClientTechEscalationResource
        extends BaseClientResource<ClientTechEscalation, ClientTechEscalationDTO, ClientTechEscalationDTO>
        implements ClientTechEscalationAPI {
    public ClientTechEscalationResource(BaseClientRepository<ClientTechEscalation, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<List<ClientTechEscalationDTO>> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid List<ClientTechEscalationDTO> inputDTOs) {
        List<ClientTechEscalationDTO> outputDTOs = inputDTOs.stream().map(inputDTO -> {
            Client client = getClientRepository().findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            ClientTechEscalation entity = toEntity(inputDTO);
            entity.setClient(client);
            ClientTechEscalation createdEntity = getBaseClientRepository().save(entity);
            ClientTechEscalationDTO outPutDTO = toDTO(createdEntity);
            return outPutDTO;
        }).toList();
        return ResponseEntity.ok().body(outputDTOs);
    }

    @Override
    public ResponseEntity<ClientTechEscalationDTO> getById(@PathVariable("clientId")Long clientId,@PathVariable("id") Long id) {

        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ClientTechEscalationDTO>> getAll(@PathVariable("clientId")Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<ClientTechEscalationDTO>> search(@PathVariable("clientId")Long clientId,
            Map<String, String> searchCriteria, Optional<Set<String>> attributesOpt, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<ClientTechEscalationDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody @Valid ClientTechEscalationDTO inputDTOs) {
        return super.update(clientId, id, inputDTOs);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected ClientTechEscalation toEntity(ClientTechEscalationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected ClientTechEscalationDTO toDTO(ClientTechEscalation entity) {
        return getMapper().toDTO(entity);
    }

}
