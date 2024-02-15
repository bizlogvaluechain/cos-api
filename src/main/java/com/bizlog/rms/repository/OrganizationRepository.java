package com.bizlog.rms.repository;

import com.bizlog.rms.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query("SELECT org.id FROM Organization org")
    List<Long> getOrganizationIds();
}
