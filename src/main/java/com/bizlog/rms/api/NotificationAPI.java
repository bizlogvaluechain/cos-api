package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.notification.NotificationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/{clientId}/notification")
public interface NotificationAPI extends BaseAPI<NotificationDTO, NotificationDTO> {
    @PostMapping
    ResponseEntity<NotificationDTO> create(Long clientId, NotificationDTO notificationDTO);

    @GetMapping("/{id}")
    ResponseEntity<NotificationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<NotificationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<NotificationDTO> update(Long Id, Long id, NotificationDTO notificationDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}