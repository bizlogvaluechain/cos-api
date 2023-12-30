package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientinfo.BillingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingInfoRepository extends JpaRepository<BillingInfo, Long> {
}
