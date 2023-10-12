package com.bizlog.rms.dto.SOP_TAT.subLists;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class MinorActivites {

    private String qC;

    private String imageCapture;

    private String signatureCapture;

    private String oTPValidation;

    private String evaluation;

    private String segregation;

    private String grouping;
    private String grading;

}
