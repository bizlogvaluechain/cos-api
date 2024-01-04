package com.bizlog.rms.repository;

import com.bizlog.rms.entities.sop.SOPActivity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface SOPActivityRepository extends BaseClientRepository<SOPActivity, Long> {
}