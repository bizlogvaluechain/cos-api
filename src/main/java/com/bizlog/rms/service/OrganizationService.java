package com.bizlog.rms.service;

import com.bizlog.rms.entities.Organization;
import com.bizlog.rms.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationService(final OrganizationRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public Organization save(Organization organization) {
        return repository.save(organization);
    }
}
