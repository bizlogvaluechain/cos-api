package com.bizlog.rms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientDTO {

    private Long id;

    @NotBlank(message = "name should not be null")
    @Size(min = 3, max = 250)
    private String name;

    @NotBlank(message = "description should not be null")
    @Size(min = 4, max = 250)
    private String description;

    @Email(message = "Email should be a valid")
    private String email;

    private String phoneNumber;

    @NotBlank(message = "domain name should not be blank")
    @Size(min = 4, max = 250)
    private String domainName;

    @NotNull(message = "active value should not be null")
    private Boolean active;

    private String type;

}
