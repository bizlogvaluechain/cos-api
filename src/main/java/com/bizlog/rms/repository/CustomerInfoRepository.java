package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientinfo.CustomerInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends BaseClientRepository<CustomerInfo, Long> {

}
