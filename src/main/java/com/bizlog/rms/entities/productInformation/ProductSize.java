package com.bizlog.rms.entities.productInformation;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ProductSize {
    private Boolean mini;
    private Boolean small;
    private Boolean medium;
    private Boolean large;
    private Boolean xLarge;
}
