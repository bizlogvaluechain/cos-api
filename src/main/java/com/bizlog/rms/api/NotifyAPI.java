package com.bizlog.rms.api;

import com.bizlog.rms.dto.NotifyApiDTO;
import com.bizlog.rms.dto.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/notify")
public interface NotifyAPI extends BaseAPI<NotifyApiDTO,NotifyApiDTO>{

    @PostMapping
    ResponseEntity<NotifyApiDTO> create(Long clientId, NotifyApiDTO notifyApiDTO);

    @GetMapping("/{id}")
    ResponseEntity<NotifyApiDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<NotifyApiDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<NotifyApiDTO> update(Long Id, Long id, NotifyApiDTO notifyApiDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<NotifyApiDTO> getByClientId(Long clientId);
}
