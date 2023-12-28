package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientinfo.BillingInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingInfoRepository extends BaseClientRepository<BillingInfo, Long> {
}
