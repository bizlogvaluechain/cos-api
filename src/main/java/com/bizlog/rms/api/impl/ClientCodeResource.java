package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientCodeAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.ClientCodeDTO;
import com.bizlog.rms.entities.clientinfo.ClientCode;
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

@Slf4j
@RestController
public class ClientCodeResource extends BaseClientResource<ClientCode, ClientCodeDTO, ClientCodeDTO>
        implements ClientCodeAPI {
    public ClientCodeResource(BaseClientRepository<ClientCode, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    protected void preValidate(Long clientId, ClientCodeDTO payloadDTO, OperationType operationType) {
        super.preValidate(clientId, payloadDTO, operationType);
        if (operationType == OperationType.CREATE) {
            getBaseClientRepository()
                    .findByClient(getClientRepository().findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId)))
                    .ifPresent(X -> {
                        throw new AlreadyExistException(clientId);
                    });
        }
    }

    @Override
    protected ClientCode toEntity(ClientCodeDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected ClientCodeDTO toDTO(ClientCode entity) {
        return getMapper().toDTO(entity);
    }

    @Transactional
    @Override
    public ResponseEntity<ClientCodeDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody ClientCodeDTO payloadDTO) {
        return super.create(clientId, payloadDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<ClientCodeDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody ClientCodeDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<ClientCodeDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ClientCodeDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<ClientCodeDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }
}
