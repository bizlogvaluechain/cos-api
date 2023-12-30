package com.bizlog.rms.api;

import com.bizlog.rms.dto.clientinfo.BillingInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/billing-info")
public interface BillingInfoAPI {
    @PostMapping
    ResponseEntity<BillingInfoDTO> create(BillingInfoDTO inputDTO);

    @GetMapping("/{id}")
    ResponseEntity<BillingInfoDTO> getById(Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long id);
}
