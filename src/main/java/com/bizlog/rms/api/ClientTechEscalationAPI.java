package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.ClientTechEscalationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequestMapping(value = "/api/v1/cos/{clientId}/client-tech-escalation")
public interface ClientTechEscalationAPI extends BaseAPI<ClientTechEscalationDTO, ClientTechEscalationDTO> {

    @PostMapping
    ResponseEntity<List<ClientTechEscalationDTO>> create(Long clientId, List<ClientTechEscalationDTO> inputDTOS);

    @GetMapping("/{id}")
    ResponseEntity<ClientTechEscalationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ClientTechEscalationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ClientTechEscalationDTO> update(Long Id, Long id, ClientTechEscalationDTO inputDTOs);

    @GetMapping("/search")
    ResponseEntity<PageResponse<ClientTechEscalationDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Optional<Set<String>> attributesOpt, Pageable pageable);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
