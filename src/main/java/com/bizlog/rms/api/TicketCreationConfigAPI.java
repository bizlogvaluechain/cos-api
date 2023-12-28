package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.TicketCreationConfigDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/ticket-creation-config")
public interface TicketCreationConfigAPI extends BaseAPI<TicketCreationConfigDTO, TicketCreationConfigDTO> {
    @PostMapping
    ResponseEntity<TicketCreationConfigDTO> create(Long clientId, TicketCreationConfigDTO ticketCreationConfigDTO);

    @GetMapping("/{id}")
    ResponseEntity<TicketCreationConfigDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<TicketCreationConfigDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<TicketCreationConfigDTO> update(Long Id, Long id, TicketCreationConfigDTO ticketCreationConfigDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
