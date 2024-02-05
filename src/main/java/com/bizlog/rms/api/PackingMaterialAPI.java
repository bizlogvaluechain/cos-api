package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;

import com.bizlog.rms.dto.product.PackingMaterialDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/cos/{clientId}/packing_material")
public interface PackingMaterialAPI extends BaseAPI<PackingMaterialDTO,PackingMaterialDTO> {
    @PostMapping
    ResponseEntity<PackingMaterialDTO> create(Long clientId, PackingMaterialDTO packingMaterialDTO);

    @GetMapping("/{id}")
    ResponseEntity<PackingMaterialDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<PackingMaterialDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<PackingMaterialDTO> update(Long Id, Long id, PackingMaterialDTO packingMaterialDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

}
