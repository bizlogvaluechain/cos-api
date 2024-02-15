package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientEngagementAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientengagement.ClientEngagementDTO;
import com.bizlog.rms.entities.clientengagement.ClientEngagement;
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

@RestController
@Slf4j
public class ClientEngagementResource extends
        BaseClientResource<ClientEngagement, ClientEngagementDTO, ClientEngagementDTO> implements ClientEngagementAPI {

    public ClientEngagementResource(BaseClientRepository<ClientEngagement, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    protected void preValidate(Long clientId, ClientEngagementDTO payloadDTO, OperationType operationType) {
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
    protected ClientEngagement toEntity(ClientEngagementDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected ClientEngagementDTO toDTO(ClientEngagement entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    @Transactional
    public ResponseEntity<ClientEngagementDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody ClientEngagementDTO payloadDTO) {
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<ClientEngagementDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("clientId") Long id, @RequestBody ClientEngagementDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<ClientEngagementDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    public ResponseEntity<ClientEngagementDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ClientEngagementDTO>> getAll(@PathVariable("clientId") Long clientId,
            @RequestBody Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

}
