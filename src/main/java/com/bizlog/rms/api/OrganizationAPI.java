package com.bizlog.rms.api;

import com.bizlog.rms.dto.OrganizationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/v1/organization")
public interface OrganizationAPI {

    @PostMapping
    ResponseEntity<OrganizationDTO> create(@RequestBody OrganizationDTO organizationDTO);

    @GetMapping("/{id}")
    ResponseEntity<OrganizationDTO> getById(Long id);

}
