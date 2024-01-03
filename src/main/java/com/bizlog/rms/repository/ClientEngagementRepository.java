package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientengagement.ClientEngagement;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientEngagementRepository extends BaseClientRepository<ClientEngagement, Long> {
}
