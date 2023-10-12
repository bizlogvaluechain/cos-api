package com.bizlog.rms.repository;

import com.bizlog.rms.entities.productInformation.ProductInformation;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInformatiomRepository extends BaseClientRepository<ProductInformation, Long> {
}
