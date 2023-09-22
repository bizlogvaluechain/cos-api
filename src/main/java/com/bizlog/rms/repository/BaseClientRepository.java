package com.bizlog.rms.repository;

import com.bizlog.rms.entities.Client;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseClientRepository<V, K> extends JpaRepository<V, Long>, JpaSpecificationExecutor<V> {
    Optional<V> findByClientAndId(@NotNull Client client, String id);

    List<V> deleteAllByClient(@NotNull Client client);

    List<V> findAllByClient(@NotNull Client client);

    Page<V> findAllByClient(@NotNull Client client, Pageable pageable);

    Optional<V> findByClient(@NotNull Client client);

    long countByClient(@NotNull Client client);
}
