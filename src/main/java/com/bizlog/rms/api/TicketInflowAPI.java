package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.TicketInflowDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/ticket-creation-config")
public interface TicketInflowAPI extends BaseAPI<TicketInflowDTO, TicketInflowDTO> {
    @PostMapping
    ResponseEntity<TicketInflowDTO> create(Long clientId, TicketInflowDTO ticketCreationConfigDTO);

    @GetMapping("/{id}")
    ResponseEntity<TicketInflowDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<TicketInflowDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<TicketInflowDTO> update(Long Id, Long id, TicketInflowDTO ticketCreationConfigDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
    @GetMapping("/getByClientId")
    ResponseEntity<TicketInflowDTO> getByClientId(Long clientId);
}
