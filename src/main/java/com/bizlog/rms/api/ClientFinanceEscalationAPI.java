package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.ClientFinanceEscalationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequestMapping(value = "/api/v1/cos/{clientId}/client-finance-escalation")
public interface ClientFinanceEscalationAPI extends BaseAPI<ClientFinanceEscalationDTO, ClientFinanceEscalationDTO> {

    @PostMapping
    ResponseEntity<List<ClientFinanceEscalationDTO>> create(Long clientId, List<ClientFinanceEscalationDTO> inputDTOS);

    @GetMapping("/{id}")
    ResponseEntity<ClientFinanceEscalationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ClientFinanceEscalationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ClientFinanceEscalationDTO> update(Long Id, Long id, ClientFinanceEscalationDTO inputDTOs);

    @GetMapping("/search")
    ResponseEntity<PageResponse<ClientFinanceEscalationDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Optional<Set<String>> attributesOpt, Pageable pageable);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<ClientFinanceEscalationDTO> getByClientId(Long clientId);
}
