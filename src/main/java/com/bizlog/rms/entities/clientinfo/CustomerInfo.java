package com.bizlog.rms.entities.clientinfo;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "client_info", uniqueConstraints = @UniqueConstraint(columnNames = { "id", "client_id" }))
public class CustomerInfo extends BaseClientEntity {
    @Column(name = "clientName", nullable = false)
    private String clientName;
    @Column(name = "legalEntityType", nullable = false)
    private String legalEntityType;
    @Column(name = "clientBrandName", nullable = false)
    private String clientBrandName;
    @Column(name = "clientSector", nullable = false)
    private String clientSector;
    @Column(name = "companySize", nullable = false)
    private Integer companySize;
    @Column(name = "registeredYear", nullable = false)
    private Long registeredYear;
    @Column(name = "companyRevenue", nullable = false)
    private Long companyRevenue;
    @Column(name = "clientRevenueFY", nullable = false)
    private String clientRevenueFY;
    @Column(name = "gst", nullable = false)
    private String gst;
    @Column(name = "panOrAadhar", nullable = false)
    private String panOrAadhar;
    @Column(name = "gstS3Key")
    private String gstS3Key;
    @Column(name = "panOrAadharS3Key")
    private String panOrAadharS3Key;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "billingInfoId", referencedColumnName = "id")
    private List<BillingInfo> billingInfo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shipmentInfoId", referencedColumnName = "id")
    private List<ShipmentInfo> shippingAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "companyContactDetailsId", referencedColumnName = "id")
    private CompanyContactDetails companyContactDetails;

}
