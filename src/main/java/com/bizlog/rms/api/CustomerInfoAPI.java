package com.bizlog.rms.api;

import com.bizlog.rms.dto.ClientSettingDTO;
import com.bizlog.rms.dto.CustomerInfoDTO;
import com.bizlog.rms.dto.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/api/v1/client/{clientId}/customer-info")
public interface CustomerInfoAPI extends BaseAPI<CustomerInfoDTO, CustomerInfoDTO>{
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<CustomerInfoDTO> create(Long clientId, CustomerInfoDTO customerInfoDTO, MultipartFile gstFile, MultipartFile panFile,MultipartFile msmeFile);

    @GetMapping("/{id}")
    ResponseEntity<CustomerInfoDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<CustomerInfoDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<CustomerInfoDTO> update(Long Id, Long id, CustomerInfoDTO customerInfoDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
