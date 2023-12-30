package com.bizlog.rms.entities.clientinfo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "company_contact_details")
public class CompanyContactDetails {

    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "EmailId", nullable = false)
    private String emailId;
    @Column(name = "Website", nullable = false)
    private String website;
    @Column(name = "socialMediaLink", nullable = false)
    private String socialMediaLink;


}
