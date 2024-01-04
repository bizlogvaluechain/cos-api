package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientinfo.ClientHierarchy;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientHierarchyRepository extends BaseClientRepository<ClientHierarchy, Long> {
}
