package com.bizlog.rms.api.impl;

import com.bizlog.rms.dto.BaseDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.entities.BaseClientEntity;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.utils.OperationType;
import com.bizlog.rms.utils.ProjectionRow;
import com.bizlog.rms.utils.ProjectionsUtil;
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

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Slf4j
public abstract class BaseClientResource<V extends BaseClientEntity, I extends BaseDTO, O extends BaseDTO> {

    public static final String TX_MGR_NAME = "chainedGxT";

    @Autowired
    private GenericMapper mapper;



    @Autowired
    private EntityManager entityManager;

    private final BaseClientRepository<V, Long> baseClientRepository;

    @Autowired
    private ClientRepository clientRepository;

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

    public ResponseEntity<O> create(Long clientId, I payloadDTO) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("  Client not found", "id", clientId));
        preValidate(clientId, payloadDTO, OperationType.CREATE);
        V entity = toEntity(payloadDTO);
        entity.setClient(client);
        validate(clientId, payloadDTO, entity, OperationType.CREATE);
        // other logic if any
        prePersist(clientId, payloadDTO, OperationType.CREATE);
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
        prePersist(clientId,  payloadDTO, OperationType.UPDATE);
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



    public ResponseEntity<PageResponse<O>> search(Long clientId, Map<String, String> searchCriteria,
                                                  Optional<Set<String>> attributesOpt, Pageable pageable) {
        PageResponse<O> pageResponse;
        if (attributesOpt.isPresent() && !attributesOpt.get().isEmpty()) {
            pageResponse = getBaseDataWithSearchSpecAndRequiredAttr(searchCriteria, attributesOpt, pageable);
        } else {
            pageResponse = getBaseDataWithSearchSpec(searchCriteria, pageable);
        }

        return new ResponseEntity<>(Objects.requireNonNull(pageResponse), HttpStatus.OK);
    }

    private PageResponse<O> getBaseDataWithSearchSpec(Map<String, String> searchCriteria,
                                                                              Pageable pageable) {

        Specification<V> dynamicQuery = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> entry : searchCriteria.entrySet()) {
                String columnName = entry.getKey();
                String columnValue = entry.getValue();
                if (columnName.equals("page") || columnName.equals("size") || columnName.equals("attributes")
                        || columnName.equals("userId")) {
                    continue;
                }
                Path<String> columnPath = root.get(columnName);

                // Check if the value is numeric, and use equality predicate
                if (isNumeric(columnValue)) {
                    predicates.add(criteriaBuilder.equal(columnPath, Double.parseDouble(columnValue)));

                    log.info("1coloum value",columnValue);
                } else {
                    // Otherwise, use like predicate for string values
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(columnPath), "%" + columnValue.toLowerCase() + "%"));
                    log.info("2coloum value",columnValue);

                }
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
        Page<V> pageData = baseClientRepository.findAll(dynamicQuery, pageable);
        List<O> outPutDTO = pageData.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        Map<String, Object> meta = getMetaData(pageData);
        return  new PageResponse<>(meta, outPutDTO);
    }

    private PageResponse<O> getBaseDataWithSearchSpecAndRequiredAttr(Map<String, String> searchCriteria,
                                                                                             Optional<Set<String>> attributesOpt, Pageable pageable) {
        Specification<V> dynamicQuerySpec = (rootSpec, query, criteriaBuilderSpec) -> {

            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> entry : searchCriteria.entrySet()) {
                String columnName = entry.getKey();
                String columnValue = entry.getValue();
                if (columnName.equals("page") || columnName.equals("size") || columnName.equals("attributes")
                        || columnName.equals("userId")) {
                    continue;
                }
                Path<String> columnPath = rootSpec.get(columnName);
                if (isNumeric(columnValue)) {
                    predicates.add(criteriaBuilderSpec.equal(columnPath, Double.parseDouble(columnValue)));
                    log.info("3coloum value",columnValue);

                } else {
                    // Otherwise, use like predicate for string values
                    predicates.add(criteriaBuilderSpec.like(criteriaBuilderSpec.lower(columnPath),
                            "%" + columnValue.toLowerCase() + "%"));
                    log.info("4coloum value",columnValue);

                }
            }
            predicates.forEach(query::where);

            return criteriaBuilderSpec.or(predicates.toArray(new Predicate[0]));
        };

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<BaseClientEntity> root = criteriaQuery.from(BaseClientEntity.class);

        attributesOpt.ifPresent(attributes -> {
            List<Selection<?>> selectionList = ProjectionsUtil.getSelectionList(root, attributes);

            criteriaQuery.multiselect(selectionList);
        });

        criteriaQuery.where(dynamicQuerySpec.toPredicate((Root<V>) root, criteriaQuery, criteriaBuilder));

        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("priority")));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<ProjectionRow> projectionRowList = ProjectionsUtil.convert(typedQuery.getResultList());
//        List<O> outPutDTO = projectionRowList.stream()
//                .<I>map(this::toDTO)
//                .collect(Collectors.toList());
        List<O> outPutDTO = projectionRowList.stream().map(projectionRow->toDTO(projectionRow)).collect(Collectors.toList());
        Map<String, Object> meta = new HashMap<>();
        return  new PageResponse<>(meta, outPutDTO);
    }




    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
