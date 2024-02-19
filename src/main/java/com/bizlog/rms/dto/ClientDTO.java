package com.bizlog.rms.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "description should not be null")
    @Size(min = 4, max = 250)
    @JsonProperty("description")
    private String description;

    @Email(message = "Email should be a valid")
    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @NotBlank(message = "domain name should not be blank")
    @Size(min = 4, max = 250)
    @JsonProperty("domainName")
    private String domainName;

    @NotNull(message = "active value should not be null")
    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("type")
    private String type;
    @JsonProperty("dateOfOnboarding")
    private Long dateOfOnboarding;


}
