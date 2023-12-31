package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.LinehaulAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.LinehaulDTO;
import com.bizlog.rms.entities.sop.linehaul.Linehaul;
import com.bizlog.rms.repository.BaseClientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinehaulResource extends BaseClientResource<Linehaul, LinehaulDTO, LinehaulDTO> implements LinehaulAPI {
    public LinehaulResource(BaseClientRepository baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<LinehaulDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody LinehaulDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<LinehaulDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody LinehaulDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    public ResponseEntity<LinehaulDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<LinehaulDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected LinehaulDTO toDTO(Linehaul entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    protected Linehaul toEntity(LinehaulDTO dto) {
        return getMapper().toEntity(dto);
    }
}
