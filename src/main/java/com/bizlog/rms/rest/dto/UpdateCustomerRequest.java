package com.bizlog.rms.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateCustomerRequest {

    @Schema(example = "Ivan Franchin 2")
    private String name;

    @Schema(example = "ivan.franchin.2@test.com")
    @Email
    private String email;

    @Schema(example = "Porto")
    private String city;

    @Schema(example = "Street Los Angeles")
    private String street;

    @Schema(example = "20")
    private String number;
}
