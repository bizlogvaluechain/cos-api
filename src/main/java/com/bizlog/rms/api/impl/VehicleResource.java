package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.VehicleAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.VehicleDTO;
import com.bizlog.rms.entities.sop.labourtoolvechile.Vehicle;
import com.bizlog.rms.exception.AlreadyExistException;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import com.bizlog.rms.utils.OperationType;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleResource extends BaseClientResource<Vehicle, VehicleDTO, VehicleDTO> implements VehicleAPI {
    public VehicleResource(BaseClientRepository<Vehicle, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    protected void preValidate(Long clientId, VehicleDTO payloadDTO, OperationType operationType) {
        super.preValidate(clientId, payloadDTO, operationType);
        if (operationType == OperationType.CREATE) {
            getBaseClientRepository()
                    .findByOrganization(getOrganizationRepository().findById(clientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId)))
                    .ifPresent(X -> {
                        throw new AlreadyExistException(clientId);
                    });
        }
    }

    @Override
    public ResponseEntity<VehicleDTO> getById(Long clientId, Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<VehicleDTO>> getAll(Long clientId, Pageable pageable) {
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected Vehicle toEntity(VehicleDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    public ResponseEntity<VehicleDTO> create(@PathVariable("clientId") Long clientId,
            @RequestBody VehicleDTO payloadDTO) {
        payloadDTO.setClientId(clientId);
        return super.create(clientId, payloadDTO);
    }

    @Override
    public ResponseEntity<VehicleDTO> update(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id,
            @RequestBody VehicleDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<VehicleDTO> getByClientId(@PathVariable("clientId") Long clientId) {
        return super.getByClientId(clientId);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }

    @Override
    protected VehicleDTO toDTO(Vehicle entity) {
        return getMapper().toDTO(entity);
    }

}
