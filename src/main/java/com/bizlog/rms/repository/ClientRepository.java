package com.bizlog.rms.repository;

import com.bizlog.rms.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT client.id FROM Client client")
    List<Long> getClientIds();
}
