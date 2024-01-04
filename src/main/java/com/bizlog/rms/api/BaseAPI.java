package com.bizlog.rms.api;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.dto.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface BaseAPI<I extends BaseDTO, O extends BaseDTO> {

    ResponseEntity<O> create(Long clientId, I inputDTO);

    ResponseEntity<O> getById(Long clientId, Long id);

    ResponseEntity<PageResponse<O>> getAll(Long clientId, Pageable pageable);

    ResponseEntity<PageResponse<O>> search(Long clientId, Map<String, String> searchCriteria, Pageable pageable);

    ResponseEntity<PageResponse<O>> advanceSearch(String search, Optional<Set<String>> attributesOpt,
            Pageable pageable);

    ResponseEntity<O> update(Long clientId, Long id, I inputDTO);

    ResponseEntity<Void> delete(Long clientId, Long id);

    ResponseEntity<O> getByClientId(Long clientId);
}
