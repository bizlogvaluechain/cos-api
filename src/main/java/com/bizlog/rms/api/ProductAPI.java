package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.ProductDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/product")
public interface ProductAPI extends BaseAPI<ProductDTO, ProductDTO> {
    @PostMapping
    ResponseEntity<ProductDTO> create(Long clientId, ProductDTO productDTO);

    @GetMapping("/{id}")
    ResponseEntity<ProductDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ProductDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ProductDTO> update(Long Id, Long id, ProductDTO productDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
