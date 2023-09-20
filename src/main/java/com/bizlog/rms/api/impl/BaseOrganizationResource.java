package com.bizlog.rms.api.impl;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.entities.BaseOrganizationEntity;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.BaseOrganizationRepository;
import com.bizlog.rms.repository.OrganizationRepository;
import com.bizlog.rms.utils.OperationType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Slf4j
public abstract class BaseOrganizationResource<V extends BaseOrganizationEntity, I extends BaseDTO, O extends BaseDTO> {

    public static final String TX_MGR_NAME = "chainedGxT";

    @Autowired
    private GenericMapper mapper;

    private final BaseOrganizationRepository<V, Long> baseOrganizationRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public BaseOrganizationResource(BaseOrganizationRepository<V, Long> baseOrganizationRepository) {
        this.baseOrganizationRepository = baseOrganizationRepository;
    }

    protected void preValidate(Long orgId, I payloadDTO, OperationType operationType) {
    }

    protected void validate(Long orgId, I payloadDTO, V entity, OperationType operationType) {
    }

    protected void prePersist(Long orgId, I payloadDTO, OperationType operationType) {
    }

    protected void postPersist(Long orgId, I payloadDTO, OperationType operationType) {
    }

    protected V toEntity(I dto) {
        return null;
    }

    protected O toDTO(V entity) {
        return null;
    }

    public ResponseEntity<O> create(Long orgId, I payloadDTO) {
        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new ResourceNotFoundException("Org not found", "id", orgId));
        preValidate(orgId, payloadDTO, OperationType.CREATE);
        V entity = toEntity(payloadDTO);
        entity.setOrganization(org);
        validate(orgId, payloadDTO, entity, OperationType.CREATE);
        // other logic if any
        prePersist(orgId, payloadDTO, OperationType.CREATE);
        V createdEntity = getBaseOrganizationRepository().save(entity);
        postPersist(orgId, payloadDTO, OperationType.CREATE);
        O outPutDTO = toDTO(createdEntity);
        return new ResponseEntity<>(outPutDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<O> update(Long orgId, Long id, I payloadDTO) {
        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new ResourceNotFoundException("Org not found", "id", orgId));
        preValidate(orgId, payloadDTO, OperationType.UPDATE);
        V entity = toEntity(payloadDTO);
        entity.setOrganization(org);
        validate(orgId, payloadDTO, entity, OperationType.UPDATE);
        // other logic if any
        prePersist(orgId, payloadDTO, OperationType.UPDATE);
        V updatedEntity = getBaseOrganizationRepository().save(entity);
        postPersist(orgId, payloadDTO, OperationType.UPDATE);
        O outPutDTO = toDTO(updatedEntity);
        return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
    }

    public ResponseEntity<O> get(Long orgId, Long id) {
        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new ResourceNotFoundException("Org not found", "id", orgId));
        log.info("Org: {}", org);
        preValidate(orgId, null, OperationType.GET);

        validate(orgId, null, null, OperationType.GET);
        V getEntity = getBaseOrganizationRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Org not found", "id", orgId));
        O outPutDTO = toDTO(getEntity);
        return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
    }

    public ResponseEntity<PageResponse<O>> getAllConfig(Long orgId, Pageable pageable) {
        log.debug("Request to fetch entities for orgId {} and pageable {}", orgId, pageable);
        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new ResourceNotFoundException("Org not found", "id", orgId));
        preValidate(orgId, null, OperationType.GET);

        validate(orgId, null, null, OperationType.GET);
        // other logic if any
        Page<V> pageData = getBaseOrganizationRepository().findAllByOrganization(org, pageable);
        List<O> outPutDTO = pageData.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        Map<String, Object> meta = getMetaData(pageData);
        PageResponse<O> pageResponse = new PageResponse<>(meta, outPutDTO);
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

    public Map<String, Object> getMetaData(Page<V> pageData) {
        Map<String, Object> meta = new HashMap<>();
        meta.put("totalElements", pageData.getTotalElements());
        meta.put("totalPages", pageData.getTotalPages());
        meta.put("page", pageData.getNumber());
        meta.put("size", pageData.getSize());
        return meta;
    }

    public ResponseEntity<Void> delete(Long orgId, Long id) {
        Organization org = organizationRepository.findById(orgId)
                .orElseThrow(() -> new ResourceNotFoundException("Org not found", "id", orgId));
        log.info("Org: {}", org);
        preValidate(orgId, null, OperationType.DELETE);
        validate(orgId, null, null, OperationType.DELETE);
        // other logic if any
        prePersist(orgId, null, OperationType.DELETE);
        getBaseOrganizationRepository().deleteById(id);
        postPersist(orgId, null, OperationType.DELETE);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
