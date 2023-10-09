package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.Specifications.SOPActivityDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/{clientId}/sop")
public interface SOPActivityAPI extends BaseAPI<SOPActivityDTO, SOPActivityDTO> {
    @PostMapping
    ResponseEntity<SOPActivityDTO> create(Long clientId, SOPActivityDTO sopActivityDTO);

    @GetMapping("/{id}")
    ResponseEntity<SOPActivityDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<SOPActivityDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<SOPActivityDTO> update(Long Id, Long id, SOPActivityDTO sopActivityDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

}