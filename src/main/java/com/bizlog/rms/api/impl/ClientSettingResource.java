package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientSettingAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
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

import java.util.Date;
import java.util.List;
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
    public ResponseEntity<PageResponse<ClientSettingDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Pageable pageable) {
        return null;
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

    @Override
    protected ClientSetting toEntity(ClientSettingDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected ClientSettingDTO toDTO(ClientSetting entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(ClientSetting.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(ClientSetting.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(ClientSetting.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(ClientSetting.class,pageable,startDate,endDate,id);
    }


}
