package com.bizlog.rms.repository;

import com.bizlog.rms.entities.Client;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsById(Long id);
}
