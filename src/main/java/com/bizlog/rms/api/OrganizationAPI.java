package com.bizlog.rms.api;

import com.bizlog.rms.dto.OrganizationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/client")
public interface OrganizationAPI {

    @PostMapping
    ResponseEntity<OrganizationDTO> create(@RequestBody OrganizationDTO organizationDTO);

    @GetMapping("/{id}")
    ResponseEntity<OrganizationDTO> getById(@PathVariable("id") Long id);

    @GetMapping("/{id}/is-client-exist")
    ResponseEntity<Boolean> checkClientId(@PathVariable("id") Long id);

}
