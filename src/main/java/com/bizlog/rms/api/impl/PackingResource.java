package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.PackingAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.PackingDTO;
import com.bizlog.rms.entities.sop.packing.Packing;
import com.bizlog.rms.repository.BaseClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackingResource extends BaseClientResource<Packing, PackingDTO, PackingDTO> implements PackingAPI {
    public PackingResource(BaseClientRepository<Packing, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    protected Packing toEntity(PackingDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected PackingDTO toDTO(Packing entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    public ResponseEntity<PackingDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody PackingDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<PackingDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody PackingDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<PackingDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<PackingDTO>> getAll(@PathVariable("clientId") Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }
}
