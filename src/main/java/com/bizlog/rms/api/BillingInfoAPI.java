package com.bizlog.rms.api;

import com.bizlog.rms.dto.clientinfo.BillingInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/cos/billing-info")
public interface BillingInfoAPI {
    @PostMapping
    ResponseEntity<List<BillingInfoDTO>> create(@RequestBody List<BillingInfoDTO> payloadDTO);

    @GetMapping("/{id}")
    ResponseEntity<BillingInfoDTO> getById(Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long id);

    @PutMapping("/{id}")
    ResponseEntity<BillingInfoDTO> update(Long id, BillingInfoDTO updatedDTO);
}
