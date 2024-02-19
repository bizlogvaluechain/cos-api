package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.LabourDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/labour")
public interface LabourAPI extends BaseAPI<LabourDTO, LabourDTO> {

    @PostMapping
    ResponseEntity<LabourDTO> create(Long clientId, LabourDTO packingDTO);

    @GetMapping("/{id}")
    ResponseEntity<LabourDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<LabourDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<LabourDTO> update(Long Id, Long id, LabourDTO packingDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<LabourDTO> getByClientId(Long clientId);
}
