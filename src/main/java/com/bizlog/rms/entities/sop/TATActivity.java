package com.bizlog.rms.entities.sop;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TAT")
@Data
public class TATActivity extends BaseClientEntity {

    @Column(name = "IsTatRequired", nullable = false)
    private Boolean IsTatRequired;

    @Column(name = "tatBreachDueToBizlog")
    private String bizlog;
    @Column(name = "tatBreachDueToCustomer")
    private String customer;
    @Column(name = "tatBreachDueToThirdPartyLogistics")
    private String thirdPartyLogistics;
    @Column(name = "tatBreachDueToUnavoidableCircumtances")
    private String unavoidableCircumtances;



}
