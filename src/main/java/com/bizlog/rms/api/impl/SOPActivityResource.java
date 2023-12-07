package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.SOPActivityAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.SOPActivityDTO;
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

import java.util.Map;

@Slf4j
@RestController
public class SOPActivityResource extends BaseClientResource<SOPActivity, SOPActivityDTO, SOPActivityDTO>
        implements SOPActivityAPI {

    public SOPActivityResource(BaseClientRepository<SOPActivity, Long> sopActivityRepository) {
        super(sopActivityRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<SOPActivityDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid SOPActivityDTO sopActivityDTO) {
        sopActivityDTO.setClientId(clientId);
        return super.create(clientId, sopActivityDTO);
    }

    @Override
    public ResponseEntity<SOPActivityDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody @Valid SOPActivityDTO sopActivityDTO) {
        return super.update(clientId, id, sopActivityDTO);
    }

    @Override
    public ResponseEntity<SOPActivityDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<SOPActivityDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<SOPActivityDTO>> search(Long clientId, Map<String, String> searchCriteria, Pageable pageable) {
        return super.search(clientId, searchCriteria,  pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
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
