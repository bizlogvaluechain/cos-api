package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.OrganizationSettingAPI;
import com.bizlog.rms.dto.OrganizationSettingDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.entities.OrganizationSetting;
import com.bizlog.rms.repository.BaseOrganizationRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrganizationSettingResource
        extends BaseOrganizationResource<OrganizationSetting, OrganizationSettingDTO, OrganizationSettingDTO>
        implements OrganizationSettingAPI {

    public OrganizationSettingResource(
            BaseOrganizationRepository<OrganizationSetting, Long> organizationSettingRepository) {
        super(organizationSettingRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<OrganizationSettingDTO> create(@PathVariable Long orgId,
            @RequestBody @Valid OrganizationSettingDTO organizationSettingDTO) {
        return super.create(orgId, organizationSettingDTO);
    }

    @Override
    public ResponseEntity<PageResponse<OrganizationSettingDTO>> getAll(@PathVariable Long orgId, Pageable pageable) {
        return super.getAllConfig(orgId, pageable);
    }

    @Override
    public ResponseEntity<OrganizationSettingDTO> getById(@PathVariable Long orgId,
            @PathVariable("id") Long orgSetttingId) {
        return super.get(orgId, orgSetttingId);
    }

    @Transactional
    @Override
    public ResponseEntity<OrganizationSettingDTO> update(@PathVariable Long orgId, @PathVariable("id") Long id,
            @RequestBody @Valid OrganizationSettingDTO organizationSettingDTO) {
        return super.update(orgId, id, organizationSettingDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long orgId, @PathVariable("id") Long id) {
        return super.delete(orgId, id);
    }

    @Override
    public ResponseEntity<PageResponse<OrganizationSettingDTO>> search(Long orgId, Map<String, String> map, Pageable pageable) {
        return null;
    }

    @Override
    protected OrganizationSetting toEntity(OrganizationSettingDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected OrganizationSettingDTO toDTO(OrganizationSetting entity) {
        return getMapper().toDTO(entity);
    }
}
