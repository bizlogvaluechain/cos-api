package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.activity.LastMileDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/last_mile")
public interface LastMileAPI  extends BaseAPI<LastMileDTO,LastMileDTO>{
    @PostMapping
    ResponseEntity<LastMileDTO> create(Long clientId, LastMileDTO lastMileDTO);

    @GetMapping("/{id}")
    ResponseEntity<LastMileDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<LastMileDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<LastMileDTO> update(Long Id, Long id, LastMileDTO lastMileDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
