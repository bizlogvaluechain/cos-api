package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.BizlogFinanceEscalationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequestMapping(value = "/api/v1/cos/{clientId}/bizlog-finance-escalation")
public interface BizlogFinanceEscalationAPI extends BaseAPI<BizlogFinanceEscalationDTO, BizlogFinanceEscalationDTO> {

    @PostMapping
    ResponseEntity<List<BizlogFinanceEscalationDTO>> create(Long clientId, List<BizlogFinanceEscalationDTO> inputDTOS);

    @GetMapping("/{id}")
    ResponseEntity<BizlogFinanceEscalationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<BizlogFinanceEscalationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<BizlogFinanceEscalationDTO> update(Long Id, Long id, BizlogFinanceEscalationDTO inputDTOs);

    @GetMapping("/search")
    ResponseEntity<PageResponse<BizlogFinanceEscalationDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Optional<Set<String>> attributesOpt, Pageable pageable);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
    @GetMapping("/getByClientId")
    ResponseEntity<BizlogFinanceEscalationDTO> getByClientId(Long clientId);
}
