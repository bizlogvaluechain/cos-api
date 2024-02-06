package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.TATAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.TAT.TATDTO;
import com.bizlog.rms.entities.TAT.TAT;
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
public class TATResource extends BaseClientResource<TAT, TATDTO,TATDTO> implements TATAPI {
    public TATResource(BaseClientRepository<TAT, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    protected void preValidate(Long clientId, TATDTO payloadDTO, OperationType operationType) {
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
    public ResponseEntity<TATDTO> create(@PathVariable("clientId") Long clientId,
                                                 @Valid TATDTO tatdto) {
        return super.create(clientId, tatdto);
    }

    @Override
    public ResponseEntity<TATDTO> getById(@PathVariable("clientId") Long clientId,
                                                  @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<TATDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                               Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<TATDTO>> search(Long clientId, Map<String, String> searchCriteria,
                                                               Pageable pageable) {
        return super.search(clientId, searchCriteria, pageable);
    }

    @Override
    public ResponseEntity<TATDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                                 @RequestBody @Valid TATDTO tatdto) {
        return super.update(clientId, id, tatdto);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected TAT toEntity(TATDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected TATDTO toDTO(TAT entity) {
        return getMapper().toDTO(entity);
    }
}
