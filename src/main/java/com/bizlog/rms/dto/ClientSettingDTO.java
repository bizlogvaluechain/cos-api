package com.bizlog.rms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSettingDTO extends BaseDTO {

    private int ticketLimitPerDay;

}
