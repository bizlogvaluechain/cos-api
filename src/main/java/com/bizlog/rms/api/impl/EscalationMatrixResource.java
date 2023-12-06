package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.EscalationMatrixAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.escalationMatrix.EscalationMatrixDTO;
import com.bizlog.rms.entities.escalationMatrix.EscalationMatrix;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.EscalationMatrixRepository;
import com.bizlog.rms.utils.ProjectionRow;
import com.bizlog.rms.utils.ProjectionsUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class EscalationMatrixResource extends
        BaseClientResource<EscalationMatrix, EscalationMatrixDTO, EscalationMatrixDTO> implements EscalationMatrixAPI {
    public EscalationMatrixResource(BaseClientRepository<EscalationMatrix, Long> escalationMatrixRepository) {
        super(escalationMatrixRepository);
    }
    @Autowired
    private EscalationMatrixRepository escalationMatrixRepository;
    @Autowired
    private EntityManager entityManager;
    @Transactional
    @Override
    public ResponseEntity<EscalationMatrixDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid EscalationMatrixDTO escalationMatrixDTO) {
        escalationMatrixDTO.setClientId(clientId);
        return super.create(clientId, escalationMatrixDTO);
    }

    @Override
    public ResponseEntity<EscalationMatrixDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody @Valid EscalationMatrixDTO escalationMatrixDTO) {
        return super.update(clientId, id, escalationMatrixDTO);
    }

    @Override
    public ResponseEntity<EscalationMatrixDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<EscalationMatrixDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }


    @Override
    public ResponseEntity<PageResponse<EscalationMatrixDTO>> search(@PathVariable Long clientId, @RequestParam Map<String, String> searchCriteria,
                                                                        @RequestParam("attributes") Optional<Set<String>> attributesOpt, Pageable pageable) {
        PageResponse<EscalationMatrixDTO> pageResponse;
        if (attributesOpt.isPresent() && !attributesOpt.get().isEmpty()) {
            pageResponse = getEscalationDataWithSearchSpecAndRequiredAttr(searchCriteria, attributesOpt, pageable);
        } else {
            pageResponse = getEscalationDataWithSearchSpec(searchCriteria, pageable);
        }

        return new ResponseEntity<>(Objects.requireNonNull(pageResponse), HttpStatus.OK);
    }

    private PageResponse<EscalationMatrixDTO> getEscalationDataWithSearchSpec(Map<String, String> searchCriteria,
                                                                              Pageable pageable) {

        Specification<EscalationMatrix> dynamicQuery = (root, query, criteriaBuilder) -> {
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
        Page<EscalationMatrix> pageData = escalationMatrixRepository.findAll(dynamicQuery, pageable);
        List<EscalationMatrixDTO> outPutDTO = pageData.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        Map<String, Object> meta = getMetaData(pageData);
        return new PageResponse<>(meta, outPutDTO);
    }

    private PageResponse<EscalationMatrixDTO> getEscalationDataWithSearchSpecAndRequiredAttr(Map<String, String> searchCriteria,
                                                                                             Optional<Set<String>> attributesOpt, Pageable pageable) {
        Specification<EscalationMatrix> dynamicQuerySpec = (rootSpec, query, criteriaBuilderSpec) -> {

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
        Root<EscalationMatrix> root = criteriaQuery.from(EscalationMatrix.class);

        attributesOpt.ifPresent(attributes -> {
            List<Selection<?>> selectionList = ProjectionsUtil.getSelectionList(root, attributes);

            criteriaQuery.multiselect(selectionList);
        });

        criteriaQuery.where(dynamicQuerySpec.toPredicate(root, criteriaQuery, criteriaBuilder));

        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("priority")));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<ProjectionRow> projectionRowList = ProjectionsUtil.convert(typedQuery.getResultList());
        List<EscalationMatrixDTO> outPutDTO = projectionRowList.stream().map(this::toDTO).collect(Collectors.toList());

        Map<String, Object> meta = new HashMap<>();
        return new PageResponse<>(meta, outPutDTO);
    }

    private EscalationMatrixDTO toDTO(ProjectionRow projectionRow) {
        // Create an EscalationMatrixDTO from the ProjectionRow
        EscalationMatrixDTO escalationMatrixDTO = new EscalationMatrixDTO();


        escalationMatrixDTO.setAccountContactInfo(projectionRow.getContents().get("AccountContactInfo"));
        escalationMatrixDTO.setBusinessContactInfo(projectionRow.getContents().get("BusinessContactInfo"));
        escalationMatrixDTO.setItContactInfo(projectionRow.getContents().get("ItContactInfo"));
        escalationMatrixDTO.setEmergencyContactInfo(projectionRow.getContents().get("EmergencyContactInfo"));
        escalationMatrixDTO.setOpsContactInfo(projectionRow.getContents().get("OpsContactInfo"));

        return escalationMatrixDTO;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected EscalationMatrix toEntity(EscalationMatrixDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected EscalationMatrixDTO toDTO(EscalationMatrix entity) {
        return getMapper().toDTO(entity);
    }
}
