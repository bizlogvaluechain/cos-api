package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.NotificationAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.notification.NotificationDTO;
import com.bizlog.rms.entities.notification.Notification;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class NotificationResource extends BaseClientResource<Notification, NotificationDTO, NotificationDTO>
        implements NotificationAPI {
    public NotificationResource(BaseClientRepository<Notification, Long> NotificationRepository) {
        super(NotificationRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<NotificationDTO> create(@PathVariable Long clientId,
            @RequestBody @Valid NotificationDTO notificationDTO) {
        notificationDTO.setClientId(clientId);
        return super.create(clientId, notificationDTO);
    }

    @Override
    public ResponseEntity<NotificationDTO> update(@PathVariable Long clientId, Long id,
            @RequestBody @Valid NotificationDTO notificationDTO) {
        return super.update(clientId, id, notificationDTO);
    }

    @Override
    public ResponseEntity<NotificationDTO> getById(@PathVariable Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<NotificationDTO>> getAll(@PathVariable Long clientId, Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected Notification toEntity(NotificationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected NotificationDTO toDTO(Notification entity) {
        return getMapper().toDTO(entity);
    }
}