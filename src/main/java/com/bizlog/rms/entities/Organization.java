package com.bizlog.rms.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "organization")
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    private Long id;

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "phoneNumber")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @Column(length = 80)
    @JsonProperty("domainName")
    private String domainName;

    @Column(name = "active")
    @JsonProperty("active")
    private Boolean active;

    @Column(name = "dateOfOnboarding")
    @JsonProperty("dateOfOnboarding")
    private Long dateOfOnboarding;

    @Column(name = "organizationType")
    @JsonProperty("organizationType")
    private OrganizationType organizationType;

    @Column(name = "organisationId")
    @JsonProperty("parentOrganizationId")
    private Long parentOrganizationId;
}
