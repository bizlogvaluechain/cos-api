package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.NotificationAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.notification.NotificationDTO;
import com.bizlog.rms.entities.sop.notification.Notification;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class NotificationResource extends BaseClientResource<Notification, NotificationDTO, NotificationDTO>
        implements NotificationAPI {
    public NotificationResource(BaseClientRepository<Notification, Long> NotificationRepository) {
        super(NotificationRepository);
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
    @Transactional
    public ResponseEntity<NotificationDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    protected Notification toEntity(NotificationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected NotificationDTO toDTO(Notification entity) {
        return getMapper().toDTO(entity);
    }


    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(Notification.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(Notification.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(Notification.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(Notification.class,pageable,startDate,endDate,id);
    }
}
