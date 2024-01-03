package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientStagingAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.ClientStagingDTO;
import com.bizlog.rms.entities.clientinfo.ClientStaging;
import com.bizlog.rms.exception.AlreadyExistException;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.utils.OperationType;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ClientStagingResource extends BaseClientResource<ClientStaging, ClientStagingDTO, ClientStagingDTO>
        implements ClientStagingAPI {
    public ClientStagingResource(BaseClientRepository<ClientStaging, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    protected void preValidate(Long clientId, ClientStagingDTO payloadDTO, OperationType operationType) {
        super.preValidate(clientId, payloadDTO, operationType);
        if (operationType == OperationType.CREATE) {
            Optional<ClientStaging> entity = getBaseClientRepository()
                    .findByClient(getClientRepository().findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId)));
            entity.ifPresent(X -> {
                throw new AlreadyExistException(clientId);
            });
        }
    }

    @Override
    public ResponseEntity<ClientStagingDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ClientStagingDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected ClientStaging toEntity(ClientStagingDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected ClientStagingDTO toDTO(ClientStaging entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    public ResponseEntity<ClientStagingDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody ClientStagingDTO payloadDTO) {
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<ClientStagingDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody ClientStagingDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<ClientStagingDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }
}
