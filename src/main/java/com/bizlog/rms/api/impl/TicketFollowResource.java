package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.TicketFollowAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.TicketFollowDTO;
import com.bizlog.rms.entities.sop.TicketsFollow;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketFollowResource extends BaseClientResource<TicketsFollow, TicketFollowDTO, TicketFollowDTO>
        implements TicketFollowAPI {
    public TicketFollowResource(BaseClientRepository<TicketsFollow, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<TicketFollowDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid TicketFollowDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<TicketFollowDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody TicketFollowDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<TicketFollowDTO> getById(@PathVariable("clientId") Long clientId,
            @PathVariable("clientId") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<TicketFollowDTO>> getAll(@PathVariable("clientId") Long clientId,
            Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected TicketsFollow toEntity(TicketFollowDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected TicketFollowDTO toDTO(TicketsFollow entity) {
        return getMapper().toDTO(entity);
    }
}
