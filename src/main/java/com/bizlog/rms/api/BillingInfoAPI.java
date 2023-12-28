package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.BillingInfoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/billing-info")
public interface BillingInfoAPI extends BaseAPI<BillingInfoDTO, BillingInfoDTO> {
    @PostMapping
    ResponseEntity<BillingInfoDTO> create(Long clientId, BillingInfoDTO inputDTO);

    @GetMapping("/{id}")
    ResponseEntity<BillingInfoDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<BillingInfoDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<BillingInfoDTO> update(Long clientId, Long id, BillingInfoDTO inputDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
