package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.CustomerInfoAPI;
import com.bizlog.rms.dto.clientinfo.CustomerInfoDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.entities.clientinfo.CustomerInfo;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.service.S3Service;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
public class CustomerInfoResource extends BaseClientResource<CustomerInfo, CustomerInfoDTO, CustomerInfoDTO>
        implements CustomerInfoAPI {

    private final S3Service s3Service;

    public CustomerInfoResource(BaseClientRepository<CustomerInfo, Long> clientSettingRepository, S3Service s3Service) {
        super(clientSettingRepository);
        this.s3Service = s3Service;
    }

    @Transactional
    @Override
    public ResponseEntity<CustomerInfoDTO> create(@PathVariable Long clientId,
            @RequestBody @Valid CustomerInfoDTO customerInfoDTO) {
        return super.create(clientId, customerInfoDTO);
    }

    private void createResourceInS3(CustomerInfoDTO customerInfoDTO, MultipartFile gstFile,
            MultipartFile panOrAadharFile) {
        String gstS3Key = s3Service.uploadFileToS3(gstFile);
        String panOrAadharS3Key = s3Service.uploadFileToS3(panOrAadharFile);
        customerInfoDTO.setGstS3Key(gstS3Key);
        customerInfoDTO.setPanOrAadharS3Key(panOrAadharS3Key);
    }

    @Override
    public ResponseEntity<PageResponse<CustomerInfoDTO>> getAll(@PathVariable Long clientId, Pageable pageable) {
        log.info("get all data");
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<PageResponse<CustomerInfoDTO>> search(Long clientId, Map<String, String> searchCriteria,
            Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerInfoDTO> getById(@PathVariable Long clientId,
            @PathVariable("id") Long customerInfoId) {
        return super.get(clientId, customerInfoId);
    }

    @Transactional
    @Override
    public ResponseEntity<CustomerInfoDTO> update(@PathVariable Long clientId, @PathVariable("id") Long id,
            @RequestBody @Valid CustomerInfoDTO customerInfoDTO) {
        return super.update(clientId, id, customerInfoDTO);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected CustomerInfo toEntity(CustomerInfoDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected CustomerInfoDTO toDTO(CustomerInfo entity) {
        return getMapper().toDTO(entity);
    }

}
