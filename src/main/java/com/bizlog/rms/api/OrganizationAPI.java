package com.bizlog.rms.api;

import com.bizlog.rms.dto.OrganizationDTO;
import com.bizlog.rms.entities.OrganizationType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/cos/organiztion")
public interface OrganizationAPI {

    @PostMapping
    ResponseEntity<OrganizationDTO> create(@RequestBody OrganizationDTO organizationDTO);

    @GetMapping("/{id}")
    ResponseEntity<OrganizationDTO> getById(@PathVariable("id") Long id);

    @GetMapping("/{id}/is-client-exist")
    ResponseEntity<Boolean> checkClientId(@PathVariable("id") Long id);

    @GetMapping("/type/{orgType}")
    ResponseEntity<List<OrganizationDTO>> findByOrgType(@PathVariable("orgType") OrganizationType orgType);

    @GetMapping("/type/{orgType}/parent-org/{parentOrgId}")
    ResponseEntity<List<OrganizationDTO>> findByOrgTypeAndParentOrgId(@PathVariable("orgType") OrganizationType orgType, @PathVariable("parentOrgId") Long parentOrgId);


}
