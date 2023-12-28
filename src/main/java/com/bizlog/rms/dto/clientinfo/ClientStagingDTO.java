package com.bizlog.rms.dto.clientinfo;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientStagingDTO extends BaseDTO {
    private String clientStage;
}
