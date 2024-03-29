package com.bizlog.rms.api.impl;

import com.bizlog.rms.auditlogs.MyRevisionEntity;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.dto.PageResponse;

import com.bizlog.rms.entities.BaseClientEntity;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.OrganizationRepository;
import com.bizlog.rms.rsql.CustomRsqlVisitor;
import com.bizlog.rms.service.KafkaService;
import com.bizlog.rms.utils.OperationType;

import com.bizlog.rms.utils.ProjectionRow;
import com.bizlog.rms.utils.ProjectionsUtil;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Slf4j
public abstract class BaseClientResource<V extends BaseClientEntity, I extends BaseDTO, O extends BaseDTO> {

    @Autowired
    private GenericMapper mapper;
    @Value("${spring.kafka.enabled}")
    private  boolean kafkaEnabled;

    private final BaseClientRepository<V, Long> baseClientRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private KafkaService kafkaService;

    private static final String TOPIC="On-board";

    public BaseClientResource(BaseClientRepository<V, Long> baseClientRepository) {
        this.baseClientRepository = baseClientRepository;
    }

    protected void preValidate(Long clientId, I payloadDTO, OperationType operationType) {
    }

    protected void validate(Long clientId, I payloadDTO, V entity, OperationType operationType) {
    }

    protected void prePersist(Long clientId, I payloadDTO, OperationType operationType) {
    }

    protected void postPersist(Long clientId, I payloadDTO, OperationType operationType) {
    }

    protected V toEntity(I dto) {
        return null;
    }

    protected O toDTO(V entity) {
        return null;
    }

    public Class<V> getEntityClass() {
        return null;
    }

    public O toDTO(Map<String, String> row) {
        return null;
    }

    @Transactional
    public ResponseEntity<O> create(Long clientId, I payloadDTO) {
        log.info("Request received to create an entity with org id: {} and payload: {} ",clientId, payloadDTO);
        Organization organization = organizationRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("  Client not found", "id", clientId));
        preValidate(clientId, payloadDTO, OperationType.CREATE);
        V entity = toEntity(payloadDTO);
        entity.setOrganization(organization);
        validate(clientId, payloadDTO, entity, OperationType.CREATE);
        // other logic if any
        prePersist(clientId, payloadDTO, OperationType.CREATE);
        log.info("entity--------->" + entity.toString());
        V createdEntity = getBaseClientRepository().save(entity);
        if (kafkaEnabled) {
            kafkaService.sendMessage(TOPIC, createdEntity);
        }
        postPersist(clientId, payloadDTO, OperationType.CREATE);
        O outPutDTO = toDTO(createdEntity);
        return new ResponseEntity<>(outPutDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<O> update(Long clientId, Long id, I payloadDTO) {
        log.info("Request received to update with org id: {} and entity id: {} and payloadDTO: {} ", clientId, id, payloadDTO);
        Organization organization = organizationRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        preValidate(clientId, payloadDTO, OperationType.UPDATE);
        V entity = toEntity(payloadDTO);
        entity.setOrganization(organization);
        validate(clientId, payloadDTO, entity, OperationType.UPDATE);
        // other logic if any
        prePersist(clientId, payloadDTO, OperationType.UPDATE);
        V updatedEntity = getBaseClientRepository().save(entity);
        if (kafkaEnabled) {
            kafkaService.sendMessage(TOPIC, updatedEntity);
        }
        postPersist(clientId, payloadDTO, OperationType.UPDATE);
        O outPutDTO = toDTO(updatedEntity);
        return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
    }

    public ResponseEntity<O> get(Long clientId, Long id) {
        log.info("Request received to get entity with org id: {} and entity id:{} ", clientId, id);
        Organization organization = organizationRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        log.info("Client: {}", organization);
        preValidate(clientId, null, OperationType.GET);

        validate(clientId, null, null, OperationType.GET);
        V getEntity = getBaseClientRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        O outPutDTO = toDTO(getEntity);
        return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
    }

    public ResponseEntity<PageResponse<O>> getAllConfig(Long clientId, Pageable pageable) {
        log.info("Request received to fetch entities with org id: {} and pageable:{} ", clientId, pageable);
        Organization organization = organizationRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        preValidate(clientId, null, OperationType.GET);

        validate(clientId, null, null, OperationType.GET);
        // other logic if any
        Page<V> pageData = getBaseClientRepository().findAllByOrganization(organization, pageable);
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

    public ResponseEntity<Void> delete(Long clientId, Long id) {
        log.info("Request received to delete entity with org id: {} and id:{} ", clientId, id);
        Organization organization = organizationRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        log.info("Client: {}", organization);
        preValidate(clientId, null, OperationType.DELETE);
        validate(clientId, null, null, OperationType.DELETE);
        // other logic if any
        prePersist(clientId, null, OperationType.DELETE);
        getBaseClientRepository().deleteById(id);
        postPersist(clientId, null, OperationType.DELETE);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<O> getByClientId(Long clientId) {
        V entity = baseClientRepository
                .findByOrganization(organizationRepository.findById(clientId)
                        .orElseThrow(() -> new ResourceNotFoundException("client", "clientId", clientId)))
                .orElseThrow(() -> new ResourceNotFoundException("entity", "clientId", clientId));
        O response = toDTO(entity);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<PageResponse<O>> search(Long clientId, Map<String, String> searchCriteria,
            Pageable pageable) {
        PageResponse<O> pageResponse = getBaseDataWithSearchSpec(searchCriteria, pageable);
        return new ResponseEntity<>(Objects.requireNonNull(pageResponse), HttpStatus.OK);
    }

    private PageResponse<O> getBaseDataWithSearchSpec(Map<String, String> searchCriteria, Pageable pageable) {
        Specification<V> dynamicQuery = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> entry : searchCriteria.entrySet()) {
                String columnName = entry.getKey();
                String columnValue = entry.getValue();

                if (columnName.equals("page") || columnName.equals("size") || columnName.equals("attributes")
                        || columnName.equals("userId")) {
                    continue;
                }

                Path<?> columnPath = root.get(columnName);

                Class<?> attributeType = columnPath.getJavaType();
                if (attributeType.equals(String.class)) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower((Path<String>) columnPath),
                            "%" + columnValue.toLowerCase() + "%"));
                } else if (attributeType.equals(Boolean.class)) {
                    predicates
                            .add(criteriaBuilder.equal((Path<Boolean>) columnPath, Boolean.parseBoolean(columnValue)));
                } else if (attributeType.equals(LocalDate.class)) {
                    LocalDate dateValue = LocalDate.parse(columnValue);
                    predicates.add(criteriaBuilder.equal((Path<LocalDate>) columnPath, dateValue));
                } else if (attributeType.equals(ZonedDateTime.class)) {
                    ZonedDateTime zonedDateTime = ZonedDateTime.parse(columnValue);
                    predicates.add(criteriaBuilder.equal((Path<ZonedDateTime>) columnPath, zonedDateTime));
                } else if (attributeType.equals(Integer.class) || attributeType.equals(Long.class)) {
                    predicates.add(criteriaBuilder.equal((Path<Number>) columnPath, Long.parseLong(columnValue)));
                }
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };

        Page<V> pageData = baseClientRepository.findAll(dynamicQuery, pageable);
        List<O> outPutDTO = pageData.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        Map<String, Object> meta = getMetaData(pageData);
        return new PageResponse<>(meta, outPutDTO);
    }

    public ResponseEntity<PageResponse<O>> advanceSearch(String search, Optional<Set<String>> attributesOpt,
            Pageable pageable) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<V> dynamicQuerySpec = rootNode.accept(new CustomRsqlVisitor<>());

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<V> root = criteriaQuery.from(getEntityClass());

        attributesOpt.ifPresent(attributes -> {
            List<Selection<?>> selectionList = ProjectionsUtil.getSelectionList(root, attributes);
            criteriaQuery.multiselect(selectionList);
        });
        criteriaQuery.where(dynamicQuerySpec.toPredicate(root, criteriaQuery, criteriaBuilder));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<ProjectionRow> projectionRowList = ProjectionsUtil.convert(typedQuery.getResultList());
        List<O> outPutDTO = projectionRowList.stream().map(x -> x.getContents()).map(this::toDTO)
                .collect(Collectors.toList());
        Map<String, Object> meta = new HashMap<>();
        PageResponse<O> pageResponse = new PageResponse<>(meta, outPutDTO);
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Class<V> entityClass, Pageable pageable) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<Object[]> revisions = (List<Object[]>) auditReader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();


        return ResponseEntity.ok().body(mapRevisionsToDTOs(revisions));
    }

    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Class<V> entityClass,
                                                                 Pageable pageable, Date startDate, Date endDate) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<Object[]> revisions = (List<Object[]>) auditReader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .add(AuditEntity.revisionProperty("timestamp").ge(startDate.getTime()))
                .add(AuditEntity.revisionProperty("timestamp").lt(endDate.getTime()))
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return ResponseEntity.ok().body(mapRevisionsToDTOs(revisions));
    }

    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Class<V> entityClass
            ,Pageable pageable, Date startDate, Date endDate, Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<Object[]> revisions = (List<Object[]>) auditReader.createQuery()
                .forRevisionsOfEntity(entityClass,false,true)
                .add(AuditEntity.revisionProperty("timestamp").ge(startDate.getTime()))
                .add(AuditEntity.revisionProperty("timestamp").lt(endDate.getTime()))
                .add(AuditEntity.id().eq(id))
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        List<AuditLogsDTO> auditLogsDTO=mapRevisionsToDTOs(revisions);
        return ResponseEntity.ok().body(auditLogsDTO);
    }

    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Class<V> entityClass, Pageable pageable, Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<Object[]> revisions = (List<Object[]>) auditReader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .add(AuditEntity.id().eq(id))
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return ResponseEntity.ok().body(mapRevisionsToDTOs(revisions));
    }

    private List<AuditLogsDTO> mapRevisionsToDTOs(List<Object[]> revisions) {
        List<AuditLogsDTO> auditLogsDTO = new ArrayList<>();
        revisions.forEach(x -> {
            AuditLogsDTO y = new AuditLogsDTO();
            V entity = (V) x[0];
            y.setEntityId(entity.getId());
            y.setEntity(entity.toString());
            y.setOperation(x[2].toString());
            MyRevisionEntity revisionEntity = (MyRevisionEntity) x[1];
            y.setUserName(revisionEntity.getUserName());
            y.setRevisionId(String.valueOf(revisionEntity.getId()));
            y.setTimeStamp(String.valueOf(revisionEntity.getRevisionDate()));
            auditLogsDTO.add(y);
        });
        return auditLogsDTO;
    }
}
