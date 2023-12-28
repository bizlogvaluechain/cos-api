package com.bizlog.rms.entities.clientinfo;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "company_contact_details")
public class CompanyContactDetails extends BaseClientEntity {
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "EmailId", nullable = false)
    private String emailId;
    @Column(name = "Website", nullable = false)
    private String website;
    @Column(name = "socialMediaLink", nullable = false)
    private String socialMediaLink;
}
