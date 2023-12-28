package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.CompanyContactDetailsAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.CompanyContactDetailsDTO;
import com.bizlog.rms.entities.clientinfo.CompanyContactDetails;
import com.bizlog.rms.repository.BaseClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CompanyContactDetailsResource
        extends BaseClientResource<CompanyContactDetails, CompanyContactDetailsDTO, CompanyContactDetailsDTO>
        implements CompanyContactDetailsAPI {
    public CompanyContactDetailsResource(BaseClientRepository<CompanyContactDetails, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<CompanyContactDetailsDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<CompanyContactDetailsDTO>> getAll(Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected CompanyContactDetails toEntity(CompanyContactDetailsDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected CompanyContactDetailsDTO toDTO(CompanyContactDetails entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    public ResponseEntity<CompanyContactDetailsDTO> create(Long clientId, CompanyContactDetailsDTO payloadDTO) {
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<CompanyContactDetailsDTO> update(Long clientId, Long id,
            CompanyContactDetailsDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(Long clientId, Long id) {
        return super.delete(clientId, id);
    }
}
