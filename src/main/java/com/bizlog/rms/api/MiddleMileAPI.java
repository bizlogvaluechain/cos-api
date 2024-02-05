package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.activity.MiddleMileDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/middle_mile")
public interface MiddleMileAPI extends BaseAPI<MiddleMileDTO,MiddleMileDTO> {

    @PostMapping
    ResponseEntity<MiddleMileDTO> create(Long clientId, MiddleMileDTO middleMileDTO);

    @GetMapping("/{id}")
    ResponseEntity<MiddleMileDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<MiddleMileDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<MiddleMileDTO> update(Long Id, Long id, MiddleMileDTO middleMileDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
