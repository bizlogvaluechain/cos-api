package com.bizlog.rms.api.impl;

import com.bizlog.rms.api.ClientAPI;
import com.bizlog.rms.dto.ClientDTO;
import com.bizlog.rms.entities.Client;
import com.bizlog.rms.exception.ResourceNotFoundException;
import com.bizlog.rms.mapper.GenericMapper;
import com.bizlog.rms.repository.ClientRepository;
import com.bizlog.rms.service.ClientService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ClientResource implements ClientAPI {

    private final GenericMapper mapper;
    private final ClientService clientService;
    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public ResponseEntity<ClientDTO> create(@Valid ClientDTO clientDTO) {
        Client client = mapper.toEntity(clientDTO);
        client = clientRepository.save(client);
        ClientDTO clientDTO1 = mapper.toDTO(client);
        return ResponseEntity.ok().body(clientDTO1);
    }

    @Override
    public ResponseEntity<ClientDTO> getById(@PathVariable Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found", "id", id));
        ClientDTO clientDTO = mapper.toDTO(client);
        return ResponseEntity.ok().body(clientDTO);
    }

}
