package com.bizlog.rms.entities.product;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ProductInfo _table")
public class ProductInfo extends BaseClientEntity {
    @Column(name = "product", nullable = false)
    private String product;
    @Column(name = "categories", nullable = false)
    private String categories;
    @Column(name = "subCategories", nullable = false)
    private String subCategories;
}
