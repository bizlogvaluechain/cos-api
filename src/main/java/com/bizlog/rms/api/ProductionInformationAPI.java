package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.productInformation.ProductInformationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/{clientId}/product-information")
public interface ProductionInformationAPI extends BaseAPI<ProductInformationDTO, ProductInformationDTO> {
    @PostMapping
    ResponseEntity<ProductInformationDTO> create(Long clientId, ProductInformationDTO productInformationDTO);

    @GetMapping("/{id}")
    ResponseEntity<ProductInformationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ProductInformationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ProductInformationDTO> update(Long Id, Long id, ProductInformationDTO productInformationDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
