package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.PackingDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/packing")
public interface PackingAPI extends BaseAPI<PackingDTO, PackingDTO> {
    @PostMapping
    ResponseEntity<PackingDTO> create(Long clientId, PackingDTO packingDTO);

    @GetMapping("/{id}")
    ResponseEntity<PackingDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<PackingDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<PackingDTO> update(Long Id, Long id, PackingDTO packingDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
