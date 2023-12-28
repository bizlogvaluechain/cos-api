package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientinfo.ClientStaging;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientStagingRepository extends BaseClientRepository<ClientStaging, Long> {
}
