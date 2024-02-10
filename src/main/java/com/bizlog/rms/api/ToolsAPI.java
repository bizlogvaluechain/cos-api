package com.bizlog.rms.api;

import com.bizlog.rms.dto.PageResponse;
import com.bizlog.rms.dto.SOP_TAT.ToolsDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/cos/{clientId}/tools")
public interface ToolsAPI extends BaseAPI<ToolsDTO,ToolsDTO>{

    @PostMapping
    ResponseEntity<List<ToolsDTO>> create(@PathVariable("clientId") Long clientId,
                                          @RequestBody List<ToolsDTO> toolsDTO);

    @GetMapping("/{id}")
    ResponseEntity<ToolsDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ToolsDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ToolsDTO> update(Long Id, Long id, ToolsDTO toolsDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

    @GetMapping("/getByClientId")
    ResponseEntity<ToolsDTO> getByClientId(Long clientId);
}
