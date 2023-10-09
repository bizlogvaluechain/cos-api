package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientSettingAPI;
import com.bizlog.rms.dto.ClientSettingDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.entities.ClientSetting;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class ClientSettingResource extends BaseClientResource<ClientSetting, ClientSettingDTO, ClientSettingDTO>
        implements ClientSettingAPI {

    public ClientSettingResource(BaseClientRepository<ClientSetting, Long> clientSettingRepository) {
        super(clientSettingRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<ClientSettingDTO> create(@PathVariable Long clientId,
            @RequestBody @Valid ClientSettingDTO clientSettingDTO) {
        return super.create(clientId, clientSettingDTO);
    }

    @Override
    public ResponseEntity<PageResponse<ClientSettingDTO>> getAll(@PathVariable Long clientId, Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<ClientSettingDTO> getById(@PathVariable Long clientId,
            @PathVariable("id") Long clientSetttingId) {
        return super.get(clientId, clientSetttingId);
    }

    @Transactional
    @Override
    public ResponseEntity<ClientSettingDTO> update(@PathVariable Long clientId, @PathVariable("id") Long id,
            @RequestBody @Valid ClientSettingDTO clientSettingDTO) {
        return super.update(clientId, id, clientSettingDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }


    public ResponseEntity<PageResponse<ClientSettingDTO>> search(Long clientId, Map<String, String> map,
                                                                 Pageable pageable) {
        return null;
    }


    @Override
    protected ClientSetting toEntity(ClientSettingDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected ClientSettingDTO toDTO(ClientSetting entity) {
        return getMapper().toDTO(entity);
    }

}
