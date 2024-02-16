package com.bizlog.rms.dto;

import com.bizlog.rms.entities.OrganizationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrganizationDTO {

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

    @NotBlank(message = "organization type must not be null")
    private OrganizationType organizationType;

    private Long parentOrganizationId;

    private Long dateOfOnboarding;

}
