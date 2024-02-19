package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.FinanceContactInformationAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.FinanceContactInformationDTO;
import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.entities.clientinfo.contactinformation.FinanceContactInformation;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class FinanceContactInformationResource extends
        BaseClientResource<FinanceContactInformation, FinanceContactInformationDTO, FinanceContactInformationDTO>
        implements FinanceContactInformationAPI {
    public FinanceContactInformationResource(
            BaseClientRepository<FinanceContactInformation, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<List<FinanceContactInformationDTO>> create(@PathVariable("clientId") Long clientId,
            @RequestBody List<FinanceContactInformationDTO> financeContactInformationDTOS) {
        List<FinanceContactInformationDTO> financeContactInformationDTOS1 = financeContactInformationDTOS.stream()
                .map(financeContactInformationDTO -> {
                    Organization organization = getOrganizationRepository().findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
                    FinanceContactInformation financeContactInformation = toEntity(financeContactInformationDTO);
                    financeContactInformation.setOrganization(organization);
                    FinanceContactInformation financeContactInformation1 = getBaseClientRepository()
                            .save(financeContactInformation);
                    FinanceContactInformationDTO financeContactInformationDTO1 = toDTO(financeContactInformation1);
                    return financeContactInformationDTO1;
                }).toList();
        return ResponseEntity.ok().body(financeContactInformationDTOS1);
    }

    @Override
    public ResponseEntity<FinanceContactInformationDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<FinanceContactInformationDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        log.info("client id----------->" + clientId.toString());
        log.info("pagination----------->" + pageable.toString());
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected FinanceContactInformation toEntity(FinanceContactInformationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected FinanceContactInformationDTO toDTO(FinanceContactInformation entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    public ResponseEntity<FinanceContactInformationDTO> update(@PathVariable("clientId") Long clientId,
            @PathVariable("id") Long id, @RequestBody FinanceContactInformationDTO financeContactInformationDTOS) {
        return super.update(clientId, id, financeContactInformationDTOS);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    @Transactional
    public ResponseEntity<FinanceContactInformationDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }
}
