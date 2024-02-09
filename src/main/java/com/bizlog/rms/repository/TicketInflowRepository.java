package com.bizlog.rms.repository;

import com.bizlog.rms.entities.sop.ticketInFlow.TicketInflow;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketInflowRepository extends BaseClientRepository<TicketInflow, Long> {
}
