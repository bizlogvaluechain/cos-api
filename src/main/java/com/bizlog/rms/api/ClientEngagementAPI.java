package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientengagement.ClientEngagementDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/client-engagement")
public interface ClientEngagementAPI extends BaseAPI<ClientEngagementDTO, ClientEngagementDTO> {
    @PostMapping
    ResponseEntity<ClientEngagementDTO> create(Long clientId, ClientEngagementDTO inputDTO);

    @GetMapping("/{id}")
    ResponseEntity<ClientEngagementDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ClientEngagementDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ClientEngagementDTO> update(Long clientId, Long id, ClientEngagementDTO inputDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<ClientEngagementDTO> getByClientId(Long clientId);
}
