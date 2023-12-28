package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientStagingAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.ClientStagingDTO;
import com.bizlog.rms.entities.clientinfo.ClientStaging;
import com.bizlog.rms.repository.BaseClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientStagingResource extends BaseClientResource<ClientStaging, ClientStagingDTO, ClientStagingDTO>
        implements ClientStagingAPI {
    public ClientStagingResource(BaseClientRepository<ClientStaging, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<ClientStagingDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ClientStagingDTO>> getAll(Long clientId, Pageable pageable) {
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
    public ResponseEntity<ClientStagingDTO> create(Long clientId, ClientStagingDTO payloadDTO) {
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<ClientStagingDTO> update(Long clientId, Long id, ClientStagingDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(Long clientId, Long id) {
        return super.delete(clientId, id);
    }
}
