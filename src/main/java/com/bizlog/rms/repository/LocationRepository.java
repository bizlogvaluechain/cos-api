package com.bizlog.rms.repository;

import com.bizlog.rms.entities.location.Location;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends BaseClientRepository<Location, Long> {
}
