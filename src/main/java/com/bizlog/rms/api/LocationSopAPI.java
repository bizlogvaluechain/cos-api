package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.LocationSopDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/sop/location")
public interface LocationSopAPI extends BaseAPI<LocationSopDTO,LocationSopDTO>{

    @PostMapping
    ResponseEntity<LocationSopDTO> create(Long clientId, LocationSopDTO locationSopDTO);

    @GetMapping("/{id}")
    ResponseEntity<LocationSopDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<LocationSopDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<LocationSopDTO> update(Long Id, Long id, LocationSopDTO locationSopDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
