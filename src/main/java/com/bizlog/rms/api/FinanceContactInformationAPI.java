package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.clientinfo.FinanceContactInformationDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(value = "/api/v1/cos/client/{clientId}/contact-info/finance")
public interface FinanceContactInformationAPI extends BaseAPI<FinanceContactInformationDTO,FinanceContactInformationDTO>{

    @PostMapping
    ResponseEntity<List<FinanceContactInformationDTO>> create(Long clientId, List<FinanceContactInformationDTO> inputDTO);

    @GetMapping("/{id}")
    ResponseEntity<FinanceContactInformationDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<FinanceContactInformationDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<FinanceContactInformationDTO> update(Long clientId, Long id, FinanceContactInformationDTO inputDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);
}
