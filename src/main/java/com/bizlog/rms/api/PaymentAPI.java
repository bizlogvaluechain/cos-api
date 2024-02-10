package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.PaymentDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/payment")
public interface PaymentAPI extends BaseAPI<PaymentDTO, PaymentDTO> {

    @PostMapping
    ResponseEntity<PaymentDTO> create(Long clientId, PaymentDTO paymentDTO);

    @GetMapping("/{id}")
    ResponseEntity<PaymentDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<PaymentDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<PaymentDTO> update(Long Id, Long id, PaymentDTO paymentDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
    @GetMapping("/getByClientId")
    ResponseEntity<PaymentDTO> getByClientId(Long clientId);
}
