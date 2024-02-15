package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.OrganizationAPI;
import com.bizlog.rms.dto.OrganizationDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.OrganizationRepository;
import com.bizlog.rms.service.ClientService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrganizationResource implements OrganizationAPI {

    private final GenericMapper mapper;
    private final ClientService clientService;
    private final OrganizationRepository organizationRepository;

    @Transactional
    @Override
    public ResponseEntity<OrganizationDTO> create(@RequestBody OrganizationDTO organizationDTO) {

        Organization organization = mapper.toEntity(organizationDTO);
        organization = organizationRepository.save(organization);
        OrganizationDTO organizationDTO1 = mapper.toDTO(organization);
        return ResponseEntity.ok().body(organizationDTO1);
    }

    @Override
    public ResponseEntity<Boolean> checkClientId(@PathVariable("id") Long id) {
        List<Long> organizationIds = organizationRepository.getOrganizationIds();
        if (organizationIds.contains(id)) {
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.ok().body(false);
    }

    @Override
    public ResponseEntity<OrganizationDTO> getById(@PathVariable("id") Long id) {
        log.info("Request received to get client by id:{}", id);
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", id));
        OrganizationDTO organizationDTO = mapper.toDTO(organization);
        log.info("Request processed successfully. ");
        return ResponseEntity.ok().body(organizationDTO);
    }

}
