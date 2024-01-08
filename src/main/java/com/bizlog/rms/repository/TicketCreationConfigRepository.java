package com.bizlog.rms.repository;

import com.bizlog.rms.entities.sop.ticketInFlow.TicketCreationConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCreationConfigRepository extends BaseClientRepository<TicketCreationConfig, Long> {
}
