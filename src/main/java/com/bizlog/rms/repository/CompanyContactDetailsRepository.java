package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientinfo.CompanyContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyContactDetailsRepository extends JpaRepository<CompanyContactDetails, Long> {
}
