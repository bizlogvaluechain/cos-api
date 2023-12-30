package com.bizlog.rms.repository;

import com.bizlog.rms.entities.clientinfo.ShipmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentInfoRepository extends JpaRepository<ShipmentInfo, Long> {
}
