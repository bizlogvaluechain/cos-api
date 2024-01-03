package com.bizlog.rms.api;

import com.bizlog.rms.dto.clientinfo.CustomerInfoDTO;
import com.bizlog.rms.dto.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/client/{clientId}/customer-info")
public interface CustomerInfoAPI extends BaseAPI<CustomerInfoDTO, CustomerInfoDTO> {
    @PostMapping
    ResponseEntity<CustomerInfoDTO> create(Long clientId, CustomerInfoDTO customerInfoDTO);

    @GetMapping("/{id}")
    ResponseEntity<CustomerInfoDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<CustomerInfoDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<CustomerInfoDTO> update(Long Id, Long id, CustomerInfoDTO customerInfoDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<CustomerInfoDTO> getByClientId(Long clientId);
}
