package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientinfo.ContactInformation;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationRepository extends BaseClientRepository<ContactInformation, Long> {
}
