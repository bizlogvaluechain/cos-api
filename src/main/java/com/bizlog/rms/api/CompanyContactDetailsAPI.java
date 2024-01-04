package com.bizlog.rms.api;

import com.bizlog.rms.dto.clientinfo.CompanyContactDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/company_contact_details")
public interface CompanyContactDetailsAPI {
    @PostMapping
    ResponseEntity<CompanyContactDetailsDTO> create(Long clientId, CompanyContactDetailsDTO inputDTO);

    @GetMapping("/{id}")
    ResponseEntity<CompanyContactDetailsDTO> getById(Long clientId, Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
