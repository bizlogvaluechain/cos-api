package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientCodeAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.ClientCodeDTO;
import com.bizlog.rms.entities.clientinfo.ClientCode;
import com.bizlog.rms.repository.BaseClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ClientCodeResource extends BaseClientResource<ClientCode, ClientCodeDTO, ClientCodeDTO>
        implements ClientCodeAPI {
    public ClientCodeResource(BaseClientRepository<ClientCode, Long> baseClientRepository) {
        super(baseClientRepository);
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
    public ResponseEntity<ClientCodeDTO> create(Long clientId, ClientCodeDTO payloadDTO) {
        return super.create(clientId, payloadDTO);
    }
    @Transactional
    @Override
    public ResponseEntity<ClientCodeDTO> update(Long clientId, Long id, ClientCodeDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }
    @Transactional
    @Override
    public ResponseEntity<Void> delete(Long clientId, Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<ClientCodeDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ClientCodeDTO>> getAll(Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }
}
