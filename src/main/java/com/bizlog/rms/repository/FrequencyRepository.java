package com.bizlog.rms.repository;

import com.bizlog.rms.entities.sop.frequency.Frequency;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequencyRepository extends BaseClientRepository<Frequency, Long> {
}
