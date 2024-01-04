package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientinfo.ClientCode;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCodeRepository extends BaseClientRepository<ClientCode, Long> {
}
