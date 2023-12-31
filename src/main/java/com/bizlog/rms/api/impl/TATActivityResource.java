package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.TATActivityAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.TATActivityDTO;
import com.bizlog.rms.entities.sop.TATActivity;
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

public class TATActivityResource extends BaseClientResource<TATActivity, TATActivityDTO, TATActivityDTO>
        implements TATActivityAPI {

    public TATActivityResource(BaseClientRepository<TATActivity, Long> tatActivityRepository) {
        super(tatActivityRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<TATActivityDTO> create(@PathVariable("clientId") Long clientId,
            @Valid TATActivityDTO tatActivityDTO) {
        return super.create(clientId, tatActivityDTO);
    }

    @Override
    public ResponseEntity<TATActivityDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<TATActivityDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<TATActivityDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Pageable pageable) {
        return super.search(clientId, searchCriteria, pageable);
    }

    @Override
    public ResponseEntity<TATActivityDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody @Valid TATActivityDTO tatActivityDTO) {
        return super.update(clientId, id, tatActivityDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected TATActivity toEntity(TATActivityDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected TATActivityDTO toDTO(TATActivity entity) {
        return getMapper().toDTO(entity);
    }

}
