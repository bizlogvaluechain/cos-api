package com.bizlog.rms.entities.sop.linehaul;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LinehaulByClient {

    private String client;
}
