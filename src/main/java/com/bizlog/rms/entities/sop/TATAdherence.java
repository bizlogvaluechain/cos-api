package com.bizlog.rms.entities.sop;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TAT")
@Data
public class TATAdherence extends BaseClientEntity {

    @Column(name = "tatAdherenceRequired", nullable = false)
    private Boolean tatAdherenceRequired;

    @Column(name = "tatBreachDueToBizlog")
    private String bizlog;
    @Column(name = "tatBreachDueToCustomer")
    private String customer;
    @Column(name = "tatBreachDueToThirdPartyLogistics")
    private String thirdPartyLogistics;
    @Column(name = "tatBreachDueToUnavoidableCircumstances")
    private String unavoidableCircumstances;

}
