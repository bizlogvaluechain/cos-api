package com.bizlog.rms.api;

import com.bizlog.rms.dto.ClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/v1/client")
public interface ClientAPI {

    @PostMapping
    ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO);

    @GetMapping("/{id}")
    ResponseEntity<ClientDTO> getById(Long id);

    @GetMapping("/checkClientId/{id}")
    ResponseEntity<Boolean> checkClientId(Long id);

}
