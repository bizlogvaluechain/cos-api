package com.bizlog.rms.entities.location;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "Region_Specific_locations_tbl")
@Data
@Audited
public class RegionSpecificLocation extends BaseClientEntity {
    @Column(name = "country")
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "City")
    private String City;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Column(name = "pinCode")
    private String pinCode;
    @Column(name = "email")
    private String email;
    @Column(name = "contact1")
    private String contact1;
    @Column(name = "contact2")
    private String contact2;

}
