package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.PaymentAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.PaymentDTO;
import com.bizlog.rms.entities.sop.Payment;
import com.bizlog.rms.repository.BaseClientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentResource extends BaseClientResource<Payment, PaymentDTO, PaymentDTO> implements PaymentAPI {
    public PaymentResource(BaseClientRepository<Payment, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<PaymentDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody @Valid PaymentDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<PaymentDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody PaymentDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<PaymentDTO> getById(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<PaymentDTO>> getAll(@PathVariable("clientId") Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected Payment toEntity(PaymentDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected PaymentDTO toDTO(Payment entity) {
        return getMapper().toDTO(entity);
    }
}
