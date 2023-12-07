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

import jakarta.persistence.criteria.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

//    protected ProjectionRow toEntity(ProjectionRowDTO projectionRowDTO)
//    {
//        return getMapper().toEntity(projectionRowDTO);
//    }
//    protected ProjectionRowDTO toDTO(ProjectionRow projectionRow)
//    {
//        return getMapper().toDTO(projectionRow);
//    }

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



    public ResponseEntity<PageResponse<O>> search(Long clientId, Map<String, String> searchCriteria, Optional<Set<String>> attributesOpt,
                                                  Pageable pageable) {
        log.debug("Request to fetch entities for clientId {} and pageable {}", clientId, pageable);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
        preValidate(clientId, null, OperationType.GET);

        validate(clientId, null, null, OperationType.GET);
        PageResponse<O> pageResponse;
        if (attributesOpt.isPresent() && !attributesOpt.get().isEmpty()) {
            pageResponse = getBaseDataWithSearchSpecAndRequiredAttr(searchCriteria, attributesOpt, pageable);
        } else {
            pageResponse = getBaseDataWithSearchSpec(searchCriteria, pageable);
        }

        return new ResponseEntity<>(Objects.requireNonNull(pageResponse), HttpStatus.OK);
    }

    private PageResponse<O> getBaseDataWithSearchSpec(Map<String, String> searchCriteria, Pageable pageable) {
        Specification<V> dynamicQuery = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> entry : searchCriteria.entrySet()) {
                String columnName = entry.getKey();
                String columnValue = entry.getValue();

                if (columnName.equals("page") || columnName.equals("size") || columnName.equals("attributes") || columnName.equals("userId")) {
                    continue;
                }

                Path<?> columnPath = root.get(columnName);

                // Determine the type of the attribute using the metamodel
                Class<?> attributeType = columnPath.getJavaType();

                // Use different predicates based on the attribute type
                if (attributeType.equals(String.class)) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower((Path<String>) columnPath), "%" + columnValue.toLowerCase() + "%"));
                } else if (attributeType.equals(Boolean.class)) {
                    predicates.add(criteriaBuilder.equal((Path<Boolean>) columnPath, Boolean.parseBoolean(columnValue)));
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

    private PageResponse<O> getBaseDataWithSearchSpecAndRequiredAttr(
            Map<String, String> searchCriteria,
            Optional<Set<String>> fieldsOpt,
            Pageable pageable) {

        Specification<V> dynamicQuery = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> entry : searchCriteria.entrySet()) {
                String columnName = entry.getKey();
                String columnValue = entry.getValue();

                if (columnName.equals("page") || columnName.equals("size") || columnName.equals("attributes") || columnName.equals("userId")) {
                    continue;
                }

                Path<?> columnPath = root.get(columnName);

                Class<?> attributeType = columnPath.getJavaType();

                // Use different predicates based on the attribute type
                if (attributeType.equals(String.class)) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower((Path<String>) columnPath), "%" + columnValue.toLowerCase() + "%"));
                } else if (attributeType.equals(Boolean.class)) {
                    predicates.add(criteriaBuilder.equal((Path<Boolean>) columnPath, Boolean.parseBoolean(columnValue)));
                } else if (attributeType.equals(LocalDate.class)) {
                    LocalDate dateValue = LocalDate.parse(columnValue);
                    predicates.add(criteriaBuilder.equal((Path<LocalDate>) columnPath, dateValue));
                } else if (attributeType.equals(ZonedDateTime.class)) {
                    ZonedDateTime zonedDateTime = ZonedDateTime.parse(columnValue);
                    predicates.add(criteriaBuilder.equal((Path<ZonedDateTime>) columnPath, zonedDateTime));
                } else if (attributeType.equals(Integer.class) || attributeType.equals(Long.class)) {
                    // Assuming both Integer and Long can be treated as numeric
                    predicates.add(criteriaBuilder.equal((Path<Number>) columnPath, Long.parseLong(columnValue)));
                }
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };

        Page<V> pageData = baseClientRepository.findAll(dynamicQuery, pageable);

        List<O> outPutDTO;
        if (fieldsOpt.isPresent() && !fieldsOpt.get().isEmpty()) {
            Set<String> fieldsToSelect = fieldsOpt.get();
            outPutDTO = pageData.getContent().stream()
                    .map(entity -> createDTOWithSelectedFields(entity, fieldsToSelect))
                    .collect(Collectors.toList());
        } else {
            outPutDTO = pageData.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        }

        Map<String, Object> meta = getMetaData(pageData);
        return new PageResponse<>(meta, outPutDTO);
    }

    private O createDTOWithSelectedFields(V entity, Set<String> fieldsToSelect) {

        return toDTOWithSelectedFields(entity, fieldsToSelect);
    }

    private O toDTOWithSelectedFields(V entity, Set<String> fieldsToSelect) {

        return null;
    }


//    private  PageResponse<O> getBaseDataWithSearchSpecAndRequiredAttr(
//            Map<String, String> searchCriteria,
//            Optional<Set<String>> attributesOpt,
//            Pageable pageable,
//            Function<ProjectionRow, O> toDTOFunction) {
//
//        Specification<V> dynamicQuerySpec = (rootSpec, query, criteriaBuilderSpec) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            for (Map.Entry<String, String> entry : searchCriteria.entrySet()) {
//                String columnName = entry.getKey();
//                String columnValue = entry.getValue();
//                if (columnName.equals("page") || columnName.equals("size") || columnName.equals("attributes")
//                        || columnName.equals("userId")) {
//                    continue;
//                }
//                Path<String> columnPath = rootSpec.get(columnName);
//
//                try {
//                    if (isNumeric(columnValue)) {
//                        predicates.add(criteriaBuilderSpec.equal(columnPath, columnValue));
//                        log.info("Numeric value for column: {}", columnValue);
//                    } else {
//                        predicates.add(criteriaBuilderSpec.like(criteriaBuilderSpec.lower(columnPath),
//                                "%" + columnValue.toLowerCase() + "%"));
//                        log.info("String value for column: {}", columnValue);
//                    }
//                } catch (NumberFormatException e) {
//                    log.warn("Invalid numeric value for column: {}", columnValue);
//                    // Handle the error or log additional information as needed
//                    // You might choose to skip this column or take alternative actions
//                }
//            }
//
//            predicates.forEach(query::where);
//
//            return criteriaBuilderSpec.or(predicates.toArray(new Predicate[0]));
//        };
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
//        Root<BaseClientEntity> root = criteriaQuery.from(BaseClientEntity.class);
//
//        attributesOpt.ifPresent(attributes -> {
//            List<Selection<?>> selectionList = ProjectionsUtil.getSelectionList(root, attributes);
//            criteriaQuery.multiselect(selectionList);
//        });
//
//        criteriaQuery.where(dynamicQuerySpec.toPredicate((Root<V>) root, criteriaQuery, criteriaBuilder));
//
//        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("priority")));
//
//        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
//        typedQuery.setFirstResult((int) pageable.getOffset());
//        typedQuery.setMaxResults(pageable.getPageSize());
//
//        List<ProjectionRow> projectionRowList = ProjectionsUtil.convert(typedQuery.getResultList());
//        List<O> outPutDTO = projectionRowList.stream().map(toDTOFunction).collect(Collectors.toList());
//
//        Map<String, Object> meta = new HashMap<>();
//        return new PageResponse<>(meta, outPutDTO);
//    }



//    private PageResponse<O> getBaseDataWithSearchSpecAndRequiredAttr(Map<String, String> searchCriteria,
//                                                                     Optional<Set<String>> attributesOpt, Pageable pageable) {
//        Specification<V> dynamicQuerySpec = (rootSpec, query, criteriaBuilderSpec) -> {
//
//            List<Predicate> predicates = new ArrayList<>();
//            for (Map.Entry<String, String> entry : searchCriteria.entrySet()) {
//                String columnName = entry.getKey();
//                String columnValue = entry.getValue();
//                if (columnName.equals("page") || columnName.equals("size") || columnName.equals("attributes")
//                        || columnName.equals("userId")) {
//                    continue;
//                }
//                Path<String> columnPath = rootSpec.get(columnName);
//                if (isNumeric(columnValue)) {
//                    predicates.add(criteriaBuilderSpec.equal(columnPath, Double.parseDouble(columnValue)));
//                    log.info("3coloum value",columnValue);
//
//                } else {
//                    // Otherwise, use like predicate for string values
//                    predicates.add(criteriaBuilderSpec.like(criteriaBuilderSpec.lower(columnPath),
//                            "%" + columnValue.toLowerCase() + "%"));
//                    log.info("4coloum value",columnValue);
//
//                }
//            }
//            predicates.forEach(query::where);
//
//            return criteriaBuilderSpec.or(predicates.toArray(new Predicate[0]));
//        };
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
//        Root<BaseClientEntity> root = criteriaQuery.from(BaseClientEntity.class);  // here entity.class
//
//        attributesOpt.ifPresent(attributes -> {
//            List<Selection<?>> selectionList = ProjectionsUtil.getSelectionList(root, attributes);
//            criteriaQuery.multiselect(selectionList);
//        });
//
//        criteriaQuery.where(dynamicQuerySpec.toPredicate((Root<V>) root, criteriaQuery, criteriaBuilder));
//
//        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("priority")));
//
//        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
//        typedQuery.setFirstResult((int) pageable.getOffset());
//        typedQuery.setMaxResults(pageable.getPageSize());
//
//        List<ProjectionRow> projectionRowList = ProjectionsUtil.convert(typedQuery.getResultList());
//        List<O> outPutDTO = projectionRowList.stream()
//                .map(this::<V>toDTO)
//                .collect(Collectors.toList());
//
//        Map<String, Object> meta = new HashMap<>();
//        return new PageResponse<>(meta, outPutDTO);
//    }
//
//    private boolean isNumeric(String str) {
//        try {
//            Double.parseDouble(str);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }

}
