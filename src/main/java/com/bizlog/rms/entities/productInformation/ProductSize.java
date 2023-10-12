package com.bizlog.rms.entities.productInformation;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ProductSize {
    private String mini;
    private String small;
    private String medium;
    private String large;
    private String xLarge;
}
