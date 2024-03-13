package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ProductionInformationAPI;
import com.bizlog.rms.dto.AuditLogsDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.productInformation.ProductInformationDTO;
import com.bizlog.rms.entities.productInformation.ProductInformation;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ProductInformationResource
        extends BaseClientResource<ProductInformation, ProductInformationDTO, ProductInformationDTO>
        implements ProductionInformationAPI {

    public ProductInformationResource(BaseClientRepository<ProductInformation, Long> productInformatiomRepository) {
        super(productInformatiomRepository);
    }

    @Transactional
    @Override
    public ResponseEntity<ProductInformationDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid ProductInformationDTO productInformationDTO) {
        productInformationDTO.setClientId(clientId);
        return super.create(clientId, productInformationDTO);
    }

    @Override
    public ResponseEntity<ProductInformationDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody @Valid ProductInformationDTO productInformationDTO) {
        return super.update(clientId, id, productInformationDTO);
    }

    @Override
    public ResponseEntity<ProductInformationDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<ProductInformationDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<ProductInformationDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Pageable pageable) {
        return super.search(clientId, searchCriteria, pageable);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected ProductInformation toEntity(ProductInformationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected ProductInformationDTO toDTO(ProductInformation entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAudits(Pageable pageable) {

        return super.getAllAudits(ProductInformation.class,pageable);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsWithId(Pageable pageable, Long id) {
        return super.getAllAuditsWithId(ProductInformation.class,pageable,id);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDate(Pageable pageable, Date startDate, Date endDate) {
        return super.getAllAuditsByDate(ProductInformation.class,pageable,startDate,endDate);
    }

    @Override
    @Transactional
    public ResponseEntity<List<AuditLogsDTO>> getAllAuditsByDateWithId(Pageable pageable, Date startDate, Date endDate, Long id) {
        return super.getAllAuditsByDateWithId(ProductInformation.class,pageable,startDate,endDate,id);
    }
}
