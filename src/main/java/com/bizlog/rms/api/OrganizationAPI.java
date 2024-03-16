package com.bizlog.rms.api;

import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.OrganizationDTO;
import com.bizlog.rms.entities.OrganizationType;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping(value = "/api/v1/cos/organization")
public interface OrganizationAPI {

    @PostMapping
    ResponseEntity<OrganizationDTO> create(@RequestBody OrganizationDTO organizationDTO);

    @GetMapping("/{id}")
    ResponseEntity<OrganizationDTO> getById(@PathVariable("id") Long id);

    @GetMapping("/{id}/is-org-exist")
    ResponseEntity<Boolean> checkOrgId(@PathVariable("id") Long id);

    @GetMapping("/type/{orgType}")
    ResponseEntity<List<OrganizationDTO>> findByOrgType(@PathVariable("orgType") OrganizationType orgType);

    @GetMapping("/type/{orgType}/parent-org/{parentOrgId}")
    ResponseEntity<List<OrganizationDTO>> findByOrgTypeAndParentOrgId(@PathVariable("orgType") OrganizationType orgType, @PathVariable("parentOrgId") Long parentOrgId);

    @GetMapping("/getAudits")
    ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) ;
    @GetMapping("/getAudits/{id}")
    ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId( Pageable pageable, Long id) ;

    @GetMapping("/getAuditsByDate")
    ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) ;

    @GetMapping("/getAuditsByDate/{id}")
    ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) ;


}
