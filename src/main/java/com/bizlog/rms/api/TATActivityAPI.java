package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.Specifications.SOPActivityDTO;
import com.bizlog.rms.dto.Specifications.TATActivityDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/tatActivities")
public interface TATActivityAPI extends BaseAPI<TATActivityDTO, TATActivityDTO> {
    @PostMapping
    ResponseEntity<TATActivityDTO> create(Long clientId, @RequestBody TATActivityDTO tatActivityDTO);

    @GetMapping("/{id}")
    ResponseEntity<TATActivityDTO> getById(Long clientId, Long id);
    @GetMapping
    ResponseEntity<PageResponse<TATActivityDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<TATActivityDTO> update(Long Id, Long id, TATActivityDTO tatActivityDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

}
