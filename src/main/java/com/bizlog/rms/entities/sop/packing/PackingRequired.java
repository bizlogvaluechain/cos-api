package com.bizlog.rms.entities.sop.packing;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PackingRequired {
    private String allProducts;
    private String selectiveProducts;
    private String notRequired;

}
