package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.OrganizationAPI;
import com.bizlog.rms.dto.OrganizationDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.OrganizationType;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.OrganizationRepository;
import com.bizlog.rms.service.ClientService;
import com.bizlog.rms.utils.OperationType;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        log.info("Request received to create an entity with organizationDTO : {} ", organizationDTO);
        preValidate(organizationDTO, OperationType.CREATE);
        Organization organization = mapper.toEntity(organizationDTO);
        organization = organizationRepository.save(organization);
        OrganizationDTO organizationDTO1 = mapper.toDTO(organization);
        return ResponseEntity.ok().body(organizationDTO1);
    }

    @Override
    public ResponseEntity<Boolean> checkOrgId(@PathVariable("id") Long id) {
        log.info("Request received to checkOrgId an entity with id : {} ", id);
        List<Long> organizationIds = organizationRepository.getOrganizationIds();
        if (organizationIds.contains(id)) {
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.ok().body(false);
    }

    @Override
    public ResponseEntity<OrganizationDTO> getById(@PathVariable Long id) {
        log.info("Request received to get org by id:{}", id);
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", id));
        OrganizationDTO organizationDTO = mapper.toDTO(organization);
        log.info("Request processed successfully. ");
        return ResponseEntity.ok().body(organizationDTO);
    }

    @Override
    public ResponseEntity<List<OrganizationDTO>> findByOrgType(@PathVariable("orgType") OrganizationType orgType) {
        log.info("Request received to findOrgType by orgType : {}", orgType);
        List<Organization> organizations = organizationRepository.findByOrganizationType(orgType);
        List<OrganizationDTO> organizationDTOs = organizations.stream().map(organization -> mapper.toDTO(organization))
                .collect(Collectors.toList());
        return new ResponseEntity<>(organizationDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrganizationDTO>> findByOrgTypeAndParentOrgId(
            @PathVariable("orgType") OrganizationType orgType, @PathVariable("parentOrgId") Long parentOrgId) {
        log.info("Request received to findOrgType and parentOrgId by orgType : {} and parentOrgId : {}",orgType, parentOrgId);
        List<Organization> organizations = organizationRepository.findByOrganizationTypeAndParentOrganizationId(orgType,
                parentOrgId);
        List<OrganizationDTO> organizationDTOs = organizations.stream().map(organization -> mapper.toDTO(organization))
                .collect(Collectors.toList());
        return new ResponseEntity<>(organizationDTOs, HttpStatus.OK);
    }

    private void preValidate(OrganizationDTO organizationDTO, OperationType operationType) {
        if (operationType.equals(OperationType.CREATE)
                && (organizationDTO.getOrganizationType().equals(OrganizationType.CLIENT)
                        || organizationDTO.getOrganizationType().equals(OrganizationType.LOGISTIC_PROVIDER))) {

            Optional.ofNullable(organizationDTO.getParentOrganizationId()).ifPresentOrElse(orgId -> {
                OrganizationType parentOrganizationType = organizationRepository.findById(orgId)
                        .map(Organization::getOrganizationType)
                        .orElseThrow(() -> new RuntimeException("Parent organization not found"));

                OrganizationType currentOrganizationType = organizationDTO.getOrganizationType();

                if (currentOrganizationType.equals(OrganizationType.CLIENT)
                        && !parentOrganizationType.equals(OrganizationType.LOGISTIC_PROVIDER)
                        || currentOrganizationType.equals(OrganizationType.LOGISTIC_PROVIDER)
                                && !parentOrganizationType.equals(OrganizationType.ROOT)) {
                    throw new RuntimeException("Invalid organization type for parent");
                }

            }, () -> {
                throw new RuntimeException("Parent organization ID is required");
            });
        }
    }

}
