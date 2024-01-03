package com.bizlog.rms.api.impl;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.dto.PageResponse;

import com.bizlog.rms.entities.BaseClientEntity;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.rsql.CustomRsqlVisitor;
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
import org.springframework.beans.factory.annotation.Autowired;
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

    public static final String TX_MGR_NAME = "chainedGxT";

    @Autowired
    private GenericMapper mapper;

    private final BaseClientRepository<V, Long> baseClientRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EntityManager entityManager;

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
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("  Client not found", "id", clientId));
        preValidate(clientId, payloadDTO, OperationType.CREATE);
        V entity = toEntity(payloadDTO);
        entity.setClient(client);
        validate(clientId, payloadDTO, entity, OperationType.CREATE);
        // other logic if any
        prePersist(clientId, payloadDTO, OperationType.CREATE);
        log.info("entity--------->" + entity.toString());
        V createdEntity = getBaseClientRepository().save(entity);
        postPersist(clientId, payloadDTO, OperationType.CREATE);
        O outPutDTO = toDTO(createdEntity);
        return new ResponseEntity<>(outPutDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<O> update(Long clientId, Long id, I payloadDTO) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        preValidate(clientId, payloadDTO, OperationType.UPDATE);
        V entity = toEntity(payloadDTO);
        entity.setClient(client);
        validate(clientId, payloadDTO, entity, OperationType.UPDATE);
        // other logic if any
        prePersist(clientId, payloadDTO, OperationType.UPDATE);
        V updatedEntity = getBaseClientRepository().save(entity);
        postPersist(clientId, payloadDTO, OperationType.UPDATE);
        O outPutDTO = toDTO(updatedEntity);
        return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
    }

    public ResponseEntity<O> get(Long clientId, Long id) {
        log.info("Request recived to process with client id: {} and id:{} ", clientId, id);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        log.info("Client: {}", client);
        preValidate(clientId, null, OperationType.GET);

        validate(clientId, null, null, OperationType.GET);
        V getEntity = getBaseClientRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        O outPutDTO = toDTO(getEntity);
        return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
    }

    public ResponseEntity<PageResponse<O>> getAllConfig(Long clientId, Pageable pageable) {
        log.debug("Request to fetch entities for clientId {} and pageable {}", clientId, pageable);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        preValidate(clientId, null, OperationType.GET);

        validate(clientId, null, null, OperationType.GET);
        // other logic if any
        Page<V> pageData = getBaseClientRepository().findAllByClient(client, pageable);
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
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        log.info("Client: {}", client);
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
                .findByClient(clientRepository.findById(clientId)
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

    // @GetMapping("/search")
    // List<User> search(@RequestParam(value = "search") String search) {
    // Node rootNode = new RSQLParser().parse(search);
    // Specification<User> spec = rootNode.accept(new CustomRsqlVisitor<>());
    // return repository.findAll(spec);
    // }

    public ResponseEntity<PageResponse<O>> advanceSearch(String search, Optional<Set<String>> attributesOpt,
            Pageable pageable) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<V> dynamicQuerySpec = rootNode.accept(new CustomRsqlVisitor<>());

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<V> root = criteriaQuery.from(getEntityClass());

        attributesOpt.ifPresent(attributes -> {
            // Create an array of Path objects representing selected attributes
            List<Selection<?>> selectionList = ProjectionsUtil.getSelectionList(root, attributes);

            // Apply projections directly on the criteria query
            criteriaQuery.multiselect(selectionList);
        });

        criteriaQuery.where(dynamicQuerySpec.toPredicate(root, criteriaQuery, criteriaBuilder));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<ProjectionRow> projectionRowList = ProjectionsUtil.convert(typedQuery.getResultList());
        List<O> outPutDTO = projectionRowList.stream().map(x -> x.getContents()).map(this::toDTO)
                .collect(Collectors.toList());

        // Page<V> pageData = baseClientRepository.findAll(dynamicQuerySpec, pageable);
        // List<O> outPutDTO = pageData.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        Map<String, Object> meta = new HashMap<>();// getMetaData(pageData);
        PageResponse<O> pageResponse = new PageResponse<>(meta, outPutDTO);
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }

}
