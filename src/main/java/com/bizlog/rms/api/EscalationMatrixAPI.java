package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;

import com.bizlog.rms.dto.escalationMatrix.EscalationMatrixDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequestMapping(value = "/api/v1/cos/{clientId}/escalation-matrix")
public interface EscalationMatrixAPI extends BaseAPI<EscalationMatrixDTO, EscalationMatrixDTO> {
    @PostMapping
    ResponseEntity<List<EscalationMatrixDTO>> create(Long clientId, List<EscalationMatrixDTO> escalationMatrixDTO);

    @GetMapping("/{id}")
    ResponseEntity<EscalationMatrixDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<EscalationMatrixDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<EscalationMatrixDTO> update(Long Id, Long id, EscalationMatrixDTO escalationMatrixDTO);

    @GetMapping("/search")
    ResponseEntity<PageResponse<EscalationMatrixDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Optional<Set<String>> attributesOpt, Pageable pageable);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
