package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.locationService.RegionSpecificLocationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/Region-specific-Location")
public interface RegionSpecificLocationAPI extends BaseAPI<RegionSpecificLocationDTO, RegionSpecificLocationDTO> {
    @PostMapping
    ResponseEntity<RegionSpecificLocationDTO> create(Long clientId,
            RegionSpecificLocationDTO regionSpecificLocationDTO);

    @GetMapping("/{id}")
    ResponseEntity<RegionSpecificLocationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<RegionSpecificLocationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<RegionSpecificLocationDTO> update(Long Id, Long id,
            RegionSpecificLocationDTO regionSpecificLocationDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<RegionSpecificLocationDTO> getByClientId(Long clientId);
}
