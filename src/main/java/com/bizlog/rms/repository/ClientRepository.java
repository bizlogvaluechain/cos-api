package com.bizlog.rms.repository;

import com.bizlog.rms.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
