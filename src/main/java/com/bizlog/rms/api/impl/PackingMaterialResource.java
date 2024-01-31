package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.PackingMaterialAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.product.PackingMaterialDTO;
import com.bizlog.rms.entities.product.PackingMaterial;
import com.bizlog.rms.repository.BaseClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackingMaterialResource extends BaseClientResource<PackingMaterial, PackingMaterialDTO,PackingMaterialDTO> implements PackingMaterialAPI {
    public PackingMaterialResource(BaseClientRepository<PackingMaterial, Long> baseClientRepository) {
        super(baseClientRepository);
    }
    @Override
    public ResponseEntity<PackingMaterialDTO> create(@PathVariable("clientId") Long clientId,
                                              @RequestBody PackingMaterialDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<PackingMaterialDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
                                              @RequestBody PackingMaterialDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<PackingMaterialDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<PackingMaterialDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected PackingMaterialDTO toDTO(PackingMaterial entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected PackingMaterial toEntity(PackingMaterialDTO dto) {
        return getMapper().toEntity(dto);
    }
}
