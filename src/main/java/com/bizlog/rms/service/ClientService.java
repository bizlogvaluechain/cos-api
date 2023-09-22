package com.bizlog.rms.service;

import com.bizlog.rms.entities.Client;
import com.bizlog.rms.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(final ClientRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public Client save(Client client) {
        return repository.save(client);
    }
}
