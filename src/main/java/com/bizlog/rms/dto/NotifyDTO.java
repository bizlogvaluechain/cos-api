package com.bizlog.rms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotifyDTO {
    private String userName;
    private String toEmail;
    private String mobile;
}
