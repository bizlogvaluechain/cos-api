package com.bizlog.rms.api;

import com.bizlog.rms.dto.OrganizationSettingDTO;
import com.bizlog.rms.dto.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/org/{orgId}/organization-setting")
public interface OrganizationSettingAPI extends BaseAPI<OrganizationSettingDTO, OrganizationSettingDTO> {

    @PostMapping
    ResponseEntity<OrganizationSettingDTO> create(Long orgId, OrganizationSettingDTO organizationSettingDTO);

    @GetMapping("/{id}")
    ResponseEntity<OrganizationSettingDTO> getById(Long orgId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<OrganizationSettingDTO>> getAll(Long orgId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<OrganizationSettingDTO> update(Long orgId, Long id, OrganizationSettingDTO organizationSettingDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long orgId, Long id);

}
