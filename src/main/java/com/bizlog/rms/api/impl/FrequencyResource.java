package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.FrequencyAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.frequency.FrequencyDTO;
import com.bizlog.rms.entities.sop.frequency.Frequency;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@Slf4j
public class FrequencyResource extends BaseClientResource<Frequency, FrequencyDTO, FrequencyDTO>
        implements FrequencyAPI {
    public FrequencyResource(BaseClientRepository<Frequency, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    protected void preValidate(Long clientId, FrequencyDTO payloadDTO, OperationType operationType) {
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
    public ResponseEntity<FrequencyDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid FrequencyDTO frequencyDTO) {
        frequencyDTO.setClientId(clientId);
        return super.create(clientId, frequencyDTO);
    }

    @Override
    public ResponseEntity<FrequencyDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody @Valid FrequencyDTO frequencyDTO) {
        return super.update(clientId, id, frequencyDTO);
    }

    @Override
    public ResponseEntity<FrequencyDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<FrequencyDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<FrequencyDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Optional<Set<String>> attributesOpt, Pageable pageable) {
        return super.search(clientId, searchCriteria, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<FrequencyDTO>> advanceSearch(@RequestParam("search") String search,
            @RequestParam("requiredAttributes") Optional<Set<String>> attributesOpt, Pageable pageable) {
        return super.advanceSearch(search, attributesOpt, pageable);
    }

    @Override
    protected FrequencyDTO toDTO(Frequency entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected Frequency toEntity(FrequencyDTO dto) {
        return getMapper().toEntity(dto);
    }

    public Class<Frequency> getEntityClass() {
        return Frequency.class;
    }

    public FrequencyDTO toDTO(Map<String, String> row) {
        // FrequencyDTO dto = new FrequencyDTO();
        // dto.setTicketsVolume(row.get("ticketsVolume"));
        // return dto;
        return getMapper().toDTO(row);
    }
}
