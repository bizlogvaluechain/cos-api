package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientinfo.CompanyContactDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyContactDetailsRepository extends BaseClientRepository<CompanyContactDetails, Long> {
}
