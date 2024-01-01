package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.ClientStagingDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/client-staging")
public interface ClientStagingAPI extends BaseAPI<ClientStagingDTO, ClientStagingDTO> {
    @PostMapping
    ResponseEntity<ClientStagingDTO> create(Long clientId, ClientStagingDTO inputDTO);

    @GetMapping("/{id}")
    ResponseEntity<ClientStagingDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ClientStagingDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ClientStagingDTO> update(Long clientId, Long id, ClientStagingDTO inputDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
