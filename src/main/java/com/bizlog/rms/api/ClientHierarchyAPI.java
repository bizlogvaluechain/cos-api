package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.ClientHierarchyDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/client-hierarchy")
public interface ClientHierarchyAPI extends BaseAPI<ClientHierarchyDTO, ClientHierarchyDTO> {
    @PostMapping
    ResponseEntity<ClientHierarchyDTO> create(Long clientId, ClientHierarchyDTO inputDTO);

    @GetMapping("/{id}")
    ResponseEntity<ClientHierarchyDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ClientHierarchyDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ClientHierarchyDTO> update(Long clientId, Long id, ClientHierarchyDTO inputDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<ClientHierarchyDTO> getByClientId(Long clientId);
}
