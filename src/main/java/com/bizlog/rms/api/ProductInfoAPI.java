package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.ProductInfoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/product_info")
public interface ProductInfoAPI extends BaseAPI<ProductInfoDTO,ProductInfoDTO>{
    @PostMapping
    ResponseEntity<ProductInfoDTO> create(Long clientId, ProductInfoDTO productInfoDTO);

    @GetMapping("/{id}")
    ResponseEntity<ProductInfoDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ProductInfoDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ProductInfoDTO> update(Long Id, Long id, ProductInfoDTO productInfoDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
