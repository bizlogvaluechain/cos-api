package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.ProductEvalutionsDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/product_evaluation")
public interface ProductEvalutionsAPI extends BaseAPI<ProductEvalutionsDTO,ProductEvalutionsDTO> {
    @PostMapping
    ResponseEntity<ProductEvalutionsDTO> create(Long clientId, ProductEvalutionsDTO productEvalutionsDTO);

    @GetMapping("/{id}")
    ResponseEntity<ProductEvalutionsDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ProductEvalutionsDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ProductEvalutionsDTO> update(Long Id, Long id, ProductEvalutionsDTO productEvalutionsDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
