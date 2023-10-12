package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.locationService.LocationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/{clientId}/location")
public interface LocationAPI extends BaseAPI<LocationDTO, LocationDTO> {
    @PostMapping
    ResponseEntity<LocationDTO> create(Long clientId, LocationDTO locationDTO);

    @GetMapping("/{id}")
    ResponseEntity<LocationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<LocationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<LocationDTO> update(Long Id, Long id, LocationDTO locationDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
