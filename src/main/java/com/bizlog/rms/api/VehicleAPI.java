package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.VehicleDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/vehicle")
public interface VehicleAPI extends BaseAPI<VehicleDTO, VehicleDTO> {

    @PostMapping
    ResponseEntity<VehicleDTO> create(Long clientId, VehicleDTO vehicleDTO);

    @GetMapping("/{id}")
    ResponseEntity<VehicleDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<VehicleDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<VehicleDTO> update(Long Id, Long id, VehicleDTO vehicleDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
