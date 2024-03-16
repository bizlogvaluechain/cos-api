package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.NotifyAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.NotifyApiDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.entities.NotifyApi;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class NotifyApiResource extends BaseClientResource<NotifyApi, NotifyApiDTO,NotifyApiDTO> implements NotifyAPI {
    public NotifyApiResource(BaseClientRepository<NotifyApi, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    protected void preValidate(Long clientId, NotifyApiDTO payloadDTO, OperationType operationType) {
        super.preValidate(clientId, payloadDTO, operationType);
        if (operationType == OperationType.CREATE) {
            getBaseClientRepository()
                    .findByOrganization(getOrganizationRepository().findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId)))
                    .ifPresent(X -> {
                        throw new AlreadyExistException(clientId);
                    });
        }
    }

    @Transactional
    @Override
    public ResponseEntity<NotifyApiDTO> create(@PathVariable("clientId") Long clientId,
                                                  @RequestBody @Valid NotifyApiDTO notifyApiDTO) {
        notifyApiDTO.setClientId(clientId);
        return super.create(clientId, notifyApiDTO);
    }

    @Override
    public ResponseEntity<NotifyApiDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                                  @RequestBody @Valid NotifyApiDTO notificationDTO) {
        return super.update(clientId, id, notificationDTO);
    }

    @Override
    public ResponseEntity<NotifyApiDTO> getById(@PathVariable("clientId") Long clientId,
                                                   @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<NotifyApiDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                                Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<NotifyApiDTO>> search(Long clientId, Map<String, String> searchCriteria,
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
    public ResponseEntity<NotifyApiDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    protected NotifyApi toEntity(NotifyApiDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected NotifyApiDTO toDTO(NotifyApi entity) {
        return getMapper().toDTO(entity);
    }


    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(NotifyApi.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(NotifyApi.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(NotifyApi.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(NotifyApi.class,pageable,startDate,endDate,id);
    }
}
