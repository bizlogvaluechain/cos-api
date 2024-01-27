//package com.bizlog.rms.api;
//
//import com.bizlog.rms.dto.PageResponse;
//import com.bizlog.rms.dto.clientinfo.ContactInformationDTO;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping(value = "/api/v1/cos/client/{clientId}/contact-info")
//public interface ContactInformationAPI extends BaseAPI<ContactInformationDTO, ContactInformationDTO> {
//
//    @PostMapping
//    ResponseEntity<List<ContactInformationDTO>> create(Long clientId, List<ContactInformationDTO> inputDTO);
//
//    @GetMapping("/{id}")
//    ResponseEntity<ContactInformationDTO> getById(Long clientId, Long id);
//
//    @GetMapping
//    ResponseEntity<PageResponse<ContactInformationDTO>> getAll(Long clientId, Pageable pageable);
//
//    @PutMapping("/{id}")
//    ResponseEntity<ContactInformationDTO> update(Long clientId, Long id, ContactInformationDTO inputDTO);
//
//    @DeleteMapping("/{id}")
//    ResponseEntity<Void> delete(Long clientId, Long id);
//
//}
