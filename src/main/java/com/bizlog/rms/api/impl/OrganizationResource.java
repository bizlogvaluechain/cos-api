package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.OrganizationAPI;
import com.bizlog.rms.dto.OrganizationDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.OrganizationRepository;
import com.bizlog.rms.service.OrganizationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrganizationResource implements OrganizationAPI {

    private final GenericMapper mapper;
    private final OrganizationService organizationService;
    private final OrganizationRepository organizationRepository;

    @Transactional
    @Override
    public ResponseEntity<OrganizationDTO> create(@Valid OrganizationDTO organizationDTO) {
        Organization org = mapper.toEntity(organizationDTO);
        org = organizationRepository.save(org);
        OrganizationDTO organizationDTO1 = mapper.toDTO(org);
        return ResponseEntity.ok().body(organizationDTO1);
    }

    @Override
    public ResponseEntity<OrganizationDTO> getById(@PathVariable Long id) {
        Organization org = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Org not found", "id", id));
        OrganizationDTO orgDTO = mapper.toDTO(org);
        return ResponseEntity.ok().body(orgDTO);
    }

}
