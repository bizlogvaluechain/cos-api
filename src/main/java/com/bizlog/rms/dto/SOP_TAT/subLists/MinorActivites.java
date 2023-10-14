package com.bizlog.rms.dto.SOP_TAT.subLists;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class MinorActivites {

    public List<String> qC;

    public List<String> imageCapture;

    public List<String> signatureCapture;

    public List<String> oTPValidation;

    public List<String> evaluation;

    public List<String> segregation;

    public List<String> grading;

}
