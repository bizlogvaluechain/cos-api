package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.ProductDetailsDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/product_detail")
public interface ProductDetailsAPI extends BaseAPI<ProductDetailsDTO,ProductDetailsDTO>{
    @PostMapping
    ResponseEntity<ProductDetailsDTO> create(Long clientId, ProductDetailsDTO productDetailsDTO);

    @GetMapping("/{id}")
    ResponseEntity<ProductDetailsDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ProductDetailsDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ProductDetailsDTO> update(Long Id, Long id, ProductDetailsDTO productDetailsDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
