package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.ClientCodeDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/client_code")
public interface ClientCodeAPI extends BaseAPI<ClientCodeDTO, ClientCodeDTO> {
    @PostMapping
    ResponseEntity<ClientCodeDTO> create(Long clientId, ClientCodeDTO inputDTO);

    @GetMapping("/{id}")
    ResponseEntity<ClientCodeDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ClientCodeDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ClientCodeDTO> update(Long clientId, Long id, ClientCodeDTO inputDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<ClientCodeDTO> getByClientId(Long clientId);
}
