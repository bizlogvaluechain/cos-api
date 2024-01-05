package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.LinehaulDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/linehaul")
public interface LinehaulAPI extends BaseAPI<LinehaulDTO, LinehaulDTO> {

    @PostMapping
    ResponseEntity<LinehaulDTO> create(Long clientId, LinehaulDTO linehaulDTO);

    @GetMapping("/{id}")
    ResponseEntity<LinehaulDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<LinehaulDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<LinehaulDTO> update(Long Id, Long id, LinehaulDTO linehaulDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
