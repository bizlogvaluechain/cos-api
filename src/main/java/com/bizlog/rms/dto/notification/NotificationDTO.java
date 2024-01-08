package com.bizlog.rms.dto.notification;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO extends BaseDTO {
    private Boolean isSmsClient;
    private Boolean isEmailClient;
    private Boolean isSmsCustomer;
    private Boolean isEmailCustomer;
}
