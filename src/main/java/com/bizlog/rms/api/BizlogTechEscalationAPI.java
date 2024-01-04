package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.BizlogTechEscalationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequestMapping(value = "/api/v1/cos/{clientId}/bizlog-tech-escalation")
public interface BizlogTechEscalationAPI extends BaseAPI<BizlogTechEscalationDTO, BizlogTechEscalationDTO> {

    @PostMapping
    ResponseEntity<List<BizlogTechEscalationDTO>> create(Long clientId, List<BizlogTechEscalationDTO> inputDTOS);

    @GetMapping("/{id}")
    ResponseEntity<BizlogTechEscalationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<BizlogTechEscalationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<BizlogTechEscalationDTO> update(Long Id, Long id, BizlogTechEscalationDTO inputDTOs);

    @GetMapping("/search")
    ResponseEntity<PageResponse<BizlogTechEscalationDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Optional<Set<String>> attributesOpt, Pageable pageable);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
