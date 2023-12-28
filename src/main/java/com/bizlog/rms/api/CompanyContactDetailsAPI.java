package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.CompanyContactDetailsDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/company_contact_details")
public interface CompanyContactDetailsAPI extends BaseAPI<CompanyContactDetailsDTO, CompanyContactDetailsDTO> {
    @PostMapping
    ResponseEntity<CompanyContactDetailsDTO> create(Long clientId, CompanyContactDetailsDTO inputDTO);

    @GetMapping("/{id}")
    ResponseEntity<CompanyContactDetailsDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<CompanyContactDetailsDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<CompanyContactDetailsDTO> update(Long clientId, Long id, CompanyContactDetailsDTO inputDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
