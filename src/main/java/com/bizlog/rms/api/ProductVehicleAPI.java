package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.ProductVehicleDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/product_vehicle")
public interface ProductVehicleAPI extends BaseAPI<ProductVehicleDTO,ProductVehicleDTO> {
    @PostMapping
    ResponseEntity<ProductVehicleDTO> create(Long clientId, ProductVehicleDTO ProductVehicleDTO);

    @GetMapping("/{id}")
    ResponseEntity<ProductVehicleDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ProductVehicleDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ProductVehicleDTO> update(Long Id, Long id, ProductVehicleDTO ProductVehicleDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
