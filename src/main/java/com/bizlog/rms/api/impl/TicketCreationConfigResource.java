package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.TicketCreationConfigAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.TicketCreationConfigDTO;
import com.bizlog.rms.entities.ticketCreationConfig.TicketCreationConfig;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.repository.TicketCreationConfigRepository;
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

@Slf4j
@RestController
public class TicketCreationConfigResource
        extends BaseClientResource<TicketCreationConfig, TicketCreationConfigDTO, TicketCreationConfigDTO>
        implements TicketCreationConfigAPI {

    public TicketCreationConfigResource(BaseClientRepository<TicketCreationConfig, Long> ticketCreationConfigRepository) {
        super(ticketCreationConfigRepository);
    }

    @Autowired
    private TicketCreationConfigRepository ticketCreationConfigRepository;
    @Autowired
    private EntityManager entityManager;
    @Override
    public ResponseEntity<TicketCreationConfigDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<TicketCreationConfigDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<TicketCreationConfigDTO>> search(@PathVariable Long clientId, @RequestParam Map<String, String> searchCriteria,
                                                                        @RequestParam("attributes") Optional<Set<String>> attributesOpt, Pageable pageable) {
        PageResponse<TicketCreationConfigDTO> pageResponse;
        if (attributesOpt.isPresent() && !attributesOpt.get().isEmpty()) {
            pageResponse = getMasterDataWithSearchSpecAndRequiredAttr(searchCriteria, attributesOpt, pageable);
        } else {
            pageResponse = getMasterDataWithSearchSpec(searchCriteria, pageable);
        }

        return new ResponseEntity<>(Objects.requireNonNull(pageResponse), HttpStatus.OK);
    }

    private PageResponse<TicketCreationConfigDTO> getMasterDataWithSearchSpec(Map<String, String> searchCriteria,
                                                                    Pageable pageable) {

        Specification<TicketCreationConfig> dynamicQuery = (root, query, criteriaBuilder) -> {
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
        Page<TicketCreationConfig> pageData = ticketCreationConfigRepository.findAll(dynamicQuery, pageable);
        List<TicketCreationConfigDTO> outPutDTO = pageData.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        Map<String, Object> meta = getMetaData(pageData);
        return new PageResponse<>(meta, outPutDTO);
    }

    private PageResponse<TicketCreationConfigDTO> getMasterDataWithSearchSpecAndRequiredAttr(Map<String, String> searchCriteria,
                                                                                   Optional<Set<String>> attributesOpt, Pageable pageable) {
        Specification<TicketCreationConfig> dynamicQuerySpec = (rootSpec, query, criteriaBuilderSpec) -> {

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

            // Add the predicates after setting the projections
            predicates.forEach(query::where);


            // You can directly return the OR condition
            return criteriaBuilderSpec.or(predicates.toArray(new Predicate[0]));
        };

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<TicketCreationConfig> root = criteriaQuery.from(TicketCreationConfig.class);

        attributesOpt.ifPresent(attributes -> {
            // Create an array of Path objects representing selected attributes
            List<Selection<?>> selectionList = ProjectionsUtil.getSelectionList(root, attributes);

            // Apply projections directly on the criteria query
            criteriaQuery.multiselect(selectionList);
        });

        criteriaQuery.where(dynamicQuerySpec.toPredicate(root, criteriaQuery, criteriaBuilder));

        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("priority")));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<ProjectionRow> projectionRowList = ProjectionsUtil.convert(typedQuery.getResultList());
        List<TicketCreationConfigDTO> outPutDTO = projectionRowList
                .stream()
                .map(this::convertProjectionRowToTicketCreationConfig)
                .map(this::toDTO)
                .collect(Collectors.toList());

        Map<String, Object> meta = new HashMap<>();
        return new PageResponse<>(meta, outPutDTO);

    }

    private TicketCreationConfig convertProjectionRowToTicketCreationConfig(ProjectionRow projectionRow) {
        Map<String, String> contents = projectionRow.getContents();

        TicketCreationConfig ticketCreationConfig = new TicketCreationConfig();


        // ... continue setting other fields

        return ticketCreationConfig;
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
    public ResponseEntity<TicketCreationConfigDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid TicketCreationConfigDTO ticketCreationConfigDTO) {
        ticketCreationConfigDTO.setClientId(clientId);
        return super.create(clientId, ticketCreationConfigDTO);
    }

    @Override
    public ResponseEntity<TicketCreationConfigDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody @Valid TicketCreationConfigDTO ticketCreationConfigDTO) {
        return super.update(clientId, id, ticketCreationConfigDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected TicketCreationConfigDTO toDTO(TicketCreationConfig entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected TicketCreationConfig toEntity(TicketCreationConfigDTO dto) {
        return getMapper().toEntity(dto);
    }
}
