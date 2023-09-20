package com.bizlog.rms.api;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.dto.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface BaseAPI<I extends BaseDTO, O extends BaseDTO> {

    ResponseEntity<O> create(Long orgId, I inputDTO);

    ResponseEntity<O> getById(Long orgId, Long id);

    ResponseEntity<PageResponse<O>> getAll(Long orgId, Pageable pageable);

    ResponseEntity<O> update(Long orgId, Long id, I inputDTO);

    ResponseEntity<Void> delete(Long orgId, Long id);

    ResponseEntity<PageResponse<O>> search(Long orgId, Map<String, String> map, Pageable pageable);
}
