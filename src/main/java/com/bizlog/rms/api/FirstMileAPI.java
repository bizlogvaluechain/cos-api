package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.activity.FirstMileDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/first_mile")
public interface FirstMileAPI extends BaseAPI<FirstMileDTO,FirstMileDTO>{

    @PostMapping
    ResponseEntity<FirstMileDTO> create(Long clientId, FirstMileDTO firstMileDTO);

    @GetMapping("/{id}")
    ResponseEntity<FirstMileDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<FirstMileDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<FirstMileDTO> update(Long Id, Long id, FirstMileDTO firstMileDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
    @GetMapping("/getByClientId")
    ResponseEntity<FirstMileDTO> getByClientId(Long clientId);
}
