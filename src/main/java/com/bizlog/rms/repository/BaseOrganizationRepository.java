package com.bizlog.rms.repository;

import com.bizlog.rms.entities.Organization;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseOrganizationRepository<V, K> extends JpaRepository<V, Long>, JpaSpecificationExecutor<V> {
    Optional<V> findByOrganizationAndId(@NotNull Organization organization, String id);

    List<V> deleteAllByOrganization(@NotNull Organization organization);

    List<V> findAllByOrganization(@NotNull Organization organization);

    Page<V> findAllByOrganization(@NotNull Organization organization, Pageable pageable);

    Optional<V> findByOrganization(@NotNull Organization organization);

    long countByOrganization(@NotNull Organization organization);
}
