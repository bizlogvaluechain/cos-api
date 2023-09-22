package com.bizlog.rms.api;

import com.bizlog.rms.dto.ClientSettingDTO;
import com.bizlog.rms.dto.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/client/{clientId}/client-setting")
public interface ClientSettingAPI extends BaseAPI<ClientSettingDTO, ClientSettingDTO> {

    @PostMapping
    ResponseEntity<ClientSettingDTO> create(Long clientId, ClientSettingDTO clientSettingDTO);

    @GetMapping("/{id}")
    ResponseEntity<ClientSettingDTO> getById(Long clientId, Long id);

    @GetMapping
    ResponseEntity<PageResponse<ClientSettingDTO>> getAll(Long clientId, Pageable pageable);

    @PutMapping("/{id}")
    ResponseEntity<ClientSettingDTO> update(Long Id, Long id, ClientSettingDTO clientSettingDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(Long clientId, Long id);

}
