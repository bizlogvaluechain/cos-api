package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.SOPActivityAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.Specifications.SOPActivityDTO;
import com.bizlog.rms.entities.Specifications.SOPActivity;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SOPActivityResource extends BaseClientResource<SOPActivity, SOPActivityDTO, SOPActivityDTO>
        implements SOPActivityAPI {

    public SOPActivityResource(BaseClientRepository<SOPActivity, Long> sopActivityRepository) {
        super(sopActivityRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<SOPActivityDTO> create(@PathVariable Long clientId,
            @RequestBody @Valid SOPActivityDTO sopActivityDTO) {
        sopActivityDTO.setClientId(clientId);
        return super.create(clientId, sopActivityDTO);
    }

    @Override
    public ResponseEntity<SOPActivityDTO> update(@PathVariable Long clientId, Long id,
            @RequestBody @Valid SOPActivityDTO sopActivityDTO) {
        return super.update(clientId, id, sopActivityDTO);
    }

    @Override
    public ResponseEntity<SOPActivityDTO> getById(@PathVariable Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<SOPActivityDTO>> getAll(@PathVariable Long clientId, Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected SOPActivity toEntity(SOPActivityDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected SOPActivityDTO toDTO(SOPActivity entity) {
        return getMapper().toDTO(entity);
    }
}
