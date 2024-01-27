//package com.bizlog.rms.api.impl;
//
//import com.bizlog.rms.api.ContactInformationAPI;
//import com.bizlog.rms.dto.PageResponse;
//import com.bizlog.rms.dto.clientinfo.ContactInformationDTO;
//import com.bizlog.rms.entities.Client;
//import com.bizlog.rms.entities.clientinfo.contactinformation.ContactInformation;
//import com.bizlog.rms.exception.ResourceNotFoundException;
//import com.bizlog.rms.repository.BaseClientRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@Slf4j
//public class ContactInfoResource
//        extends BaseClientResource<ContactInformation, ContactInformationDTO, ContactInformationDTO>
//        implements ContactInformationAPI {
//
//    public ContactInfoResource(BaseClientRepository<ContactInformation, Long> baseClientRepository) {
//        super(baseClientRepository);
//    }
//
//    @Override
//    public ResponseEntity<List<ContactInformationDTO>> create(@PathVariable("clientId") Long clientId,
//            @RequestBody List<ContactInformationDTO> inputDTOs) {
//        List<ContactInformationDTO> outputDTOs = inputDTOs.stream().map(inputDTO -> {
//            Client client = getClientRepository().findById(clientId)
//                    .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", clientId));
//            ContactInformation entity = toEntity(inputDTO);
//            entity.setClient(client);
//            ContactInformation createdEntity = getBaseClientRepository().save(entity);
//            ContactInformationDTO outPutDTO = toDTO(createdEntity);
//            return outPutDTO;
//        }).toList();
//        return ResponseEntity.ok().body(outputDTOs);
//    }
//
//    @Override
//    public ResponseEntity<ContactInformationDTO> getById(@PathVariable("clientId") Long clientId,
//            @PathVariable("id") Long id) {
//        return super.get(clientId, id);
//    }
//
//    @Override
//    public ResponseEntity<PageResponse<ContactInformationDTO>> getAll(@PathVariable("clientId") Long clientId,
//            Pageable pageable) {
//        log.info("client id----------->" + clientId.toString());
//        log.info("pagination----------->" + pageable.toString());
//        return super.getAllConfig(clientId, pageable);
//    }
//
//    @Override
//    protected ContactInformation toEntity(ContactInformationDTO dto) {
//        return getMapper().toEntity(dto);
//    }
//
//    @Override
//    protected ContactInformationDTO toDTO(ContactInformation entity) {
//        return getMapper().toDTO(entity);
//    }
//
//    @Override
//    public ResponseEntity<ContactInformationDTO> update(@PathVariable("clientId") Long clientId,
//            @PathVariable("id") Long id, @RequestBody ContactInformationDTO payloadDTO) {
//        return super.update(clientId, id, payloadDTO);
//    }
//
//    @Override
//    public ResponseEntity<Void> delete(@PathVariable("clientId") Long clientId, @PathVariable("id") Long id) {
//        return super.delete(clientId, id);
//    }
//}
