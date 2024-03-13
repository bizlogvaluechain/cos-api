package com.bizlog.rms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyApiDTO extends BaseDTO{
    private String endPoint;
    private String authKey;
    private String payload;
}
