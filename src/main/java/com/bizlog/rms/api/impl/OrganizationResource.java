package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.OrganizationAPI;
import com.bizlog.rms.auditlogs.MyRevisionEntity;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.NotifyDTO;
import com.bizlog.rms.dto.OrganizationDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.OrganizationType;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.feignClient.NotifyCalls;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.OrganizationRepository;
import com.bizlog.rms.service.ClientService;
import com.bizlog.rms.service.KafkaService;
import com.bizlog.rms.utils.OperationType;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrganizationResource implements OrganizationAPI {

    private final GenericMapper mapper;
    private final ClientService clientService;

    @Value("${spring.kafka.enabled}")
    private  boolean kafkaEnabled;
    private final OrganizationRepository organizationRepository;
    private final KafkaService kafkaService;
    private static final String TOPIC="Organization";

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private NotifyCalls notifyCalls;
    @SuppressWarnings("PMD")
    @Transactional
    @Override
    public ResponseEntity<OrganizationDTO> create(@RequestBody OrganizationDTO organizationDTO) {
        log.info("Request received to create an entity with organizationDTO : {} ", organizationDTO);
        preValidate(organizationDTO, OperationType.CREATE);
        Organization organization = mapper.toEntity(organizationDTO);
        organization = organizationRepository.save(organization);
        OrganizationDTO organizationDTO1 = mapper.toDTO(organization);
        if(kafkaEnabled) {
            kafkaService.sendMessage(TOPIC, organization);
        }

        NotifyDTO notifyDTO = new NotifyDTO();
        notifyDTO.setToEmail(organizationDTO.getEmail());
        notifyDTO.setMobile(organizationDTO.getPhoneNumber());
        notifyDTO.setUserName(organizationDTO.getName());
        try{
            String response = notifyCalls.postOrg(notifyDTO);
        }
        catch (Exception e){
            log.error("Organization Onboarding Email UnSuccessFull", e);
        }
        return ResponseEntity.ok().body(organizationDTO1);
    }

    @Override
    public ResponseEntity<Boolean> checkOrgId(@PathVariable("id") Long id) {
        log.info("Request received to checkOrgId an entity with id : {} ", id);
        Organization organization = organizationRepository.findById(id).orElseThrow();
        if (organization.getActive()) {
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

    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {
        log.info("Request received to get audit entity with entity pageable: {}", pageable);
        List<Object[]> revisions = queryAuditRevisions(Organization.class, pageable);
        return ResponseEntity.ok().body(mapRevisionsToDTOs(revisions));
    }
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId( Pageable pageable, Long id) {
        log.info("Request received to get audit entity with entity id: {} and pageable: {}", id, pageable);
        List<Object[]> revisions = queryAuditRevisionsForUser(Organization.class, id, pageable);
        return ResponseEntity.ok().body(mapRevisionsToDTOs(revisions));
    }

    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        log.info("Request received to get audit entity with pageable: {}, startDate: {} and endDate: {}", pageable, startDate, endDate);
        List<Object[]> revisions = queryAuditRevisionsByDate(Organization.class,pageable, startDate, endDate);
        return ResponseEntity.ok().body(mapRevisionsToDTOs(revisions));
    }

    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        log.info("Request received to get audit entity with entity id: {}, pageable: {}, startDate: {}, endDate: {}", id, pageable, startDate, endDate);
        List<Object[]> revisions  = queryAuditRevisionsById(Organization.class,pageable, startDate, endDate, id);
        return ResponseEntity.ok().body(mapRevisionsToDTOs(revisions));
    }


    public List<Object[]> queryAuditRevisionsForUser(Class entityClass, Long id, Pageable pageable) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .add(AuditEntity.id().eq(id))
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }
    public List<Object[]> queryAuditRevisions(Class entityClass, Pageable pageable) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }
    public List<Object[]> queryAuditRevisionsByDate(Class entityClass,Pageable pageable, Date startDate, Date endDate) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .add(AuditEntity.revisionProperty("timestamp").ge(startDate.getTime()))
                .add(AuditEntity.revisionProperty("timestamp").lt(endDate.getTime()))
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    public List<Object[]> queryAuditRevisionsById(Class entityClass,Pageable pageable, Date startDate, Date endDate, Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .add(AuditEntity.revisionProperty("timestamp").ge(startDate.getTime()))
                .add(AuditEntity.revisionProperty("timestamp").lt(endDate.getTime()))
                .add(AuditEntity.id().eq(id))
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }



    private List<AuditLogsDTO> mapRevisionsToDTOs(List<Object[]> revisions) {
        List<AuditLogsDTO> auditLogsDTO = new ArrayList<>();
        revisions.forEach(x -> {
            AuditLogsDTO y = new AuditLogsDTO();
            Organization entity = (Organization) x[0];
            y.setEntityId(entity.getId());
            y.setEntity(entity.toString());
            y.setOperation(x[2].toString());
            MyRevisionEntity revisionEntity = (MyRevisionEntity) x[1];
            y.setUserName(revisionEntity.getUserName());
            y.setTimeStamp(String.valueOf(revisionEntity.getRevisionDate()));
            y.setRevisionId(String.valueOf(revisionEntity.getId()));
            auditLogsDTO.add(y);
        });
        return auditLogsDTO;
    }

}
