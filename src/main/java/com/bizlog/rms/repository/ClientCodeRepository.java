package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientinfo.ClientCode;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientCodeRepository extends BaseClientRepository<ClientCode, Long> {
    Optional<ClientCode> findByClientCode(String clientCode);
}
