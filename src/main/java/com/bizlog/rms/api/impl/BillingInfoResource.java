package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.BillingInfoAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.BillingInfoDTO;
import com.bizlog.rms.entities.clientinfo.BillingInfo;
import com.bizlog.rms.repository.BaseClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BillingInfoResource extends BaseClientResource<BillingInfo, BillingInfoDTO, BillingInfoDTO>
        implements BillingInfoAPI {
    public BillingInfoResource(BaseClientRepository<BillingInfo, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    protected BillingInfo toEntity(BillingInfoDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected BillingInfoDTO toDTO(BillingInfo entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    public ResponseEntity<BillingInfoDTO> create(Long clientId, BillingInfoDTO payloadDTO) {
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<BillingInfoDTO> update(Long clientId, Long id, BillingInfoDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(Long clientId, Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<BillingInfoDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<BillingInfoDTO>> getAll(Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }
}
