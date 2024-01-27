package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.OperationContactInformationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(value = "/api/v1/cos/client/{clientId}/contact-info/operation")
public interface OperationContactAPI extends BaseAPI<OperationContactInformationDTO,OperationContactInformationDTO>{

    @PostMapping
    ResponseEntity<List<OperationContactInformationDTO>> create(Long clientId, List<OperationContactInformationDTO> inputDTO);

    @GetMapping("/{id}")
    ResponseEntity<OperationContactInformationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<OperationContactInformationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<OperationContactInformationDTO> update(Long clientId, Long id, OperationContactInformationDTO inputDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
