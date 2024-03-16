package com.bizlog.rms.api;

import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.dto.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

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

    @GetMapping("/getAudits")
    ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable);

    @GetMapping("/getAudits/{id}")
    ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id);

    @GetMapping("getAuditsByDate")
    ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate);

    @GetMapping("getAuditsByDate/{id}")
    ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate,
                                                                Long id);
}
