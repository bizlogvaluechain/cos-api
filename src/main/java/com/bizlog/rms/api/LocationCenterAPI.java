package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.LocationCenterDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/location_center")
public interface LocationCenterAPI extends BaseAPI<LocationCenterDTO,LocationCenterDTO> {
    @PostMapping
    ResponseEntity<LocationCenterDTO> create(Long clientId, LocationCenterDTO locationCenterDTO);

    @GetMapping("/{id}")
    ResponseEntity<LocationCenterDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<LocationCenterDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<LocationCenterDTO> update(Long Id, Long id, LocationCenterDTO locationCenterDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
