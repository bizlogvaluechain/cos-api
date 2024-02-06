package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.NotificationAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.notification.NotificationDTO;
import com.bizlog.rms.entities.sop.notification.Notification;
import com.bizlog.rms.exception.AlreadyExistException;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.utils.OperationType;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class NotificationResource extends BaseClientResource<Notification, NotificationDTO, NotificationDTO>
        implements NotificationAPI {
    public NotificationResource(BaseClientRepository<Notification, Long> NotificationRepository) {
        super(NotificationRepository);
    }
    @Override
    protected void preValidate(Long clientId, NotificationDTO payloadDTO, OperationType operationType) {
        super.preValidate(clientId, payloadDTO, operationType);
        if (operationType == OperationType.CREATE) {
            getBaseClientRepository()
                    .findByClient(getClientRepository().findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId)))
                    .ifPresent(X -> {
                        throw new AlreadyExistException(clientId);
                    });
        }
    }

    @Transactional
    @Override
    public ResponseEntity<NotificationDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid NotificationDTO notificationDTO) {
        notificationDTO.setClientId(clientId);
        return super.create(clientId, notificationDTO);
    }

    @Override
    public ResponseEntity<NotificationDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody @Valid NotificationDTO notificationDTO) {
        return super.update(clientId, id, notificationDTO);
    }

    @Override
    public ResponseEntity<NotificationDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<NotificationDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<NotificationDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Pageable pageable) {
        return super.search(clientId, searchCriteria, pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
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
