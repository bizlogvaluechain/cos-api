package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.OperationContactAPI;
import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.OperationContactInformationDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.entities.clientinfo.contactinformation.OperationContactInformation;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.repository.BaseClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@Slf4j
public class OperationContactResource extends BaseClientResource<OperationContactInformation, OperationContactInformationDTO,OperationContactInformationDTO>implements OperationContactAPI {
    public OperationContactResource(BaseClientRepository<OperationContactInformation, Long> baseClientRepository) {
        super(baseClientRepository);
    }

    @Override
    public ResponseEntity<List<OperationContactInformationDTO>> create(@PathVariable("clientId") Long clientId,
                                                              @RequestBody List<OperationContactInformationDTO> inputDTOs) {
        List<OperationContactInformationDTO> outputDTOs = inputDTOs.stream().map(inputDTO -> {
            Client client = getClientRepository().findById(clientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
            OperationContactInformation entity = toEntity(inputDTO);
            entity.setClient(client);
            OperationContactInformation createdEntity = getBaseClientRepository().save(entity);
            OperationContactInformationDTO outPutDTO = toDTO(createdEntity);
            return outPutDTO;
        }).toList();
        return ResponseEntity.ok().body(outputDTOs);
    }

    @Override
    public ResponseEntity<OperationContactInformationDTO> getById(@PathVariable("clientId") Long clientId,
                                                         @PathVariable("id") Long id) {
        return super.get(clientId, id);
    }

    @Override
    public ResponseEntity<PageResponse<OperationContactInformationDTO>> getAll(@PathVariable("clientId") Long clientId,
                                                                      Pageable pageable) {
        log.info("client id----------->" + clientId.toString());
        log.info("pagination----------->" + pageable.toString());
        return super.getAllConfig(clientId, pageable);
    }

    @Override
    protected OperationContactInformation toEntity(OperationContactInformationDTO dto) {
        return getMapper().toEntity(dto);
    }

    @Override
    protected OperationContactInformationDTO toDTO(OperationContactInformation entity) {
        return getMapper().toDTO(entity);
    }

    @Override
    public ResponseEntity<OperationContactInformationDTO> update(@PathVariable("clientId") Long clientId,
                                                        @PathVariable("id") Long id, @RequestBody OperationContactInformationDTO payloadDTO) {
        return super.update(clientId, id, payloadDTO);
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
        return super.delete(clientId, id);
    }
}
