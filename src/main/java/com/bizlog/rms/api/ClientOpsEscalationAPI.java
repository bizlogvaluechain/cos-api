package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.ClientOpsEscalationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequestMapping(value = "/api/v1/cos/{clientId}/client-ops-escalation")
public interface ClientOpsEscalationAPI extends BaseAPI<ClientOpsEscalationDTO, ClientOpsEscalationDTO> {

    @PostMapping
    ResponseEntity<List<ClientOpsEscalationDTO>> create(Long clientId, List<ClientOpsEscalationDTO> inputDTOS);

    @GetMapping("/{id}")
    ResponseEntity<ClientOpsEscalationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ClientOpsEscalationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ClientOpsEscalationDTO> update(Long Id, Long id, ClientOpsEscalationDTO inputDTOs);

    @GetMapping("/search")
    ResponseEntity<PageResponse<ClientOpsEscalationDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Optional<Set<String>> attributesOpt, Pageable pageable);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<ClientOpsEscalationDTO> getByClientId(Long clientId);
}
