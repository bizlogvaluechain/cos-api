package com.bizlog.rms.repository;

import com.bizlog.rms.entities.escalationMatrix.EscalationMatrix;
import org.springframework.stereotype.Repository;

@Repository
public interface EscalationMatrixRepository extends BaseClientRepository<EscalationMatrix, Long> {
}
