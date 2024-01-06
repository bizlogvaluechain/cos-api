package com.bizlog.rms.dto.SOP_TAT;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO extends BaseDTO {
    private Boolean isCollectionRequired;
    private String paymentMethod;
    private Boolean IsPaymentRequired;
}
