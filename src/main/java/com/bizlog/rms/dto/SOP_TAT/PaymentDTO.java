package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO extends BaseDTO {
    private List<String> paymentMethods;
    private Long reconclliationFrequency;
    private Boolean collectRequired;
}
