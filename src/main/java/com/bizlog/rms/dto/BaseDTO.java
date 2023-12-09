package com.bizlog.rms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO {

    public Long id;

    public Long clientId;

}
