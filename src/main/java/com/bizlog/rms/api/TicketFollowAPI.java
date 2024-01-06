package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.TicketFollowDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/ticket_follow")
public interface TicketFollowAPI extends BaseAPI<TicketFollowDTO, TicketFollowDTO> {

    @PostMapping
    ResponseEntity<TicketFollowDTO> create(Long clientId, TicketFollowDTO ticketFollowDTO);

    @GetMapping("/{id}")
    ResponseEntity<TicketFollowDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<TicketFollowDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<TicketFollowDTO> update(Long Id, Long id, TicketFollowDTO ticketFollowDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
