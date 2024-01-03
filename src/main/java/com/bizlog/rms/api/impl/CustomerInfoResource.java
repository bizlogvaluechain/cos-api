package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.CustomerInfoAPI;
import com.bizlog.rms.dto.clientinfo.CustomerInfoDTO;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.entities.clientinfo.CustomerInfo;
import com.bizlog.rms.exception.AlreadyExistException;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.service.S3Service;
import com.bizlog.rms.utils.OperationType;
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
import java.util.Optional;

@Slf4j
@RestController
public class CustomerInfoResource extends BaseClientResource<CustomerInfo, CustomerInfoDTO, CustomerInfoDTO>
        implements CustomerInfoAPI {

    private final S3Service s3Service;
    public CustomerInfoResource(BaseClientRepository<CustomerInfo, Long> clientSettingRepository, S3Service s3Service) {
        super(clientSettingRepository);
        this.s3Service = s3Service;
    }

    @Override
    protected void preValidate(Long clientId, CustomerInfoDTO payloadDTO, OperationType operationType) {
        super.preValidate(clientId, payloadDTO, operationType);
        if (operationType == OperationType.CREATE) {
            Optional<CustomerInfo> entity = getBaseClientRepository()
                    .findByClient(getClientRepository().findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId)));
            entity.ifPresent(X -> {
                throw new AlreadyExistException(clientId);
            });
        }
    }



    @Transactional
    @Override
    public ResponseEntity<CustomerInfoDTO> create(@PathVariable Long clientId,
            @RequestBody @Valid CustomerInfoDTO customerInfoDTO) {
        log.info("billing Info------->" + customerInfoDTO.getBillingInfo().toString());
        return super.create(clientId, customerInfoDTO);
    }

    @Override
    public ResponseEntity<String> uploadFile(
            @PathVariable Long clientId,
            @RequestParam(value = "file") MultipartFile file,@RequestParam String fileName) {
        CustomerInfo customerInfo = getBaseClientRepository().findByClient(
                getClientRepository().findById(clientId).
                        orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId)))
                .orElseThrow(() -> new ResourceNotFoundException("entity not found", "id", clientId));
        String s3Key = createResourceInS3(file);
        if(fileName.equals("gst"))
        {
           customerInfo.setGstS3Key(s3Key);
        }
        else if(fileName.equals("aadhar")|| fileName.equals("pan")){
            customerInfo.setPanOrAadharS3Key(s3Key);
        }
        getBaseClientRepository().save(customerInfo);
        return ResponseEntity.ok().body(s3Key);
    }


    private String createResourceInS3( MultipartFile file) {
        return s3Service.uploadFileToS3(file);
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
    @Transactional
    public ResponseEntity<CustomerInfoDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
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
