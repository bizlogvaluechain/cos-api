package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.BizlogOpsEscalationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequestMapping(value = "/api/v1/cos/{clientId}/bizlog-ops-escalation")
public interface BizlogOpsEscalationAPI extends BaseAPI<BizlogOpsEscalationDTO, BizlogOpsEscalationDTO> {

    @PostMapping
    ResponseEntity<List<BizlogOpsEscalationDTO>> create(Long clientId, List<BizlogOpsEscalationDTO> inputDTOS);

    @GetMapping("/{id}")
    ResponseEntity<BizlogOpsEscalationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<BizlogOpsEscalationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<BizlogOpsEscalationDTO> update(Long Id, Long id, BizlogOpsEscalationDTO inputDTOs);

    @GetMapping("/search")
    ResponseEntity<PageResponse<BizlogOpsEscalationDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Optional<Set<String>> attributesOpt, Pageable pageable);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
    @GetMapping("/getByClientId")
    ResponseEntity<BizlogOpsEscalationDTO> getByClientId(Long clientId);
}
