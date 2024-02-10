package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.TATAdherenceDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/tatActivities")
public interface TATAdherenceAPI extends BaseAPI<TATAdherenceDTO, TATAdherenceDTO> {
    @PostMapping
    ResponseEntity<TATAdherenceDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody TATAdherenceDTO tatActivityDTO);

    @GetMapping("/{id}")
    ResponseEntity<TATAdherenceDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id);

    @GetMapping
    ResponseEntity<PageResponse<TATAdherenceDTO>> getAll(@PathVariable("clientId") Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<TATAdherenceDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            TATAdherenceDTO tatActivityDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<TATAdherenceDTO> getByClientId(Long clientId);
}
