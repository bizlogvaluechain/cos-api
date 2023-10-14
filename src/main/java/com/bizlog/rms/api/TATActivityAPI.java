package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.TATActivityDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/{clientId}/tatActivities")
public interface TATActivityAPI extends BaseAPI<TATActivityDTO, TATActivityDTO> {
    @PostMapping
    ResponseEntity<TATActivityDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody TATActivityDTO tatActivityDTO);

    @GetMapping("/{id}")
    ResponseEntity<TATActivityDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id);

    @GetMapping
    ResponseEntity<PageResponse<TATActivityDTO>> getAll(@PathVariable("clientId") Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<TATActivityDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            TATActivityDTO tatActivityDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id);

}
