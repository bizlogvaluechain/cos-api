package com.bizlog.rms.entities.activity;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name = "activity_last_Mile_tbl")
public class LastMile extends BaseClientEntity {
    @Column(name = "ActivityType")
    private String activityType;
    @Column(name = "isImageRequired")
    private Boolean isImageRequired;
    @Column(name = "isBizlogQuestion")
    private Boolean isBizlogQuestion;
    @Column(name = "bizlogQuestion")
    private List<String> bizlogQuestion;
    @Column(name = "isSelectQuestion")
    private Boolean isSelectQuestion;
    @Column(name = "selectQuestion")
    private List<String> selectQuestion;
    @Column(name = "isAddQuestion")
    private Boolean isAddQuestion;
    @Column(name = "addQuestion")
    private List<String> addQuestion;
    @Column(name = "isOtpValidation")
    private Boolean isOtpValidation;
    @Column(name = "")
    private List<String> otpValidation;
    @Column(name = "isSegregation")
    private Boolean isSegregation;
    @Column(name = "segregationCategory")
    private String segregationCategory;
    @Column(name = "segregationType")
    private String segregationType;
    @Column(name = "segregationCriteria")
    private String segregationCriteria;
    @Column(name = "isSignatureRequired")
    private Boolean isSignatureRequired;
    @Column(name = "isAppointmentRequired")
    private Boolean isAppointmentRequired;
    @Column(name = "maxNoAttempts")
    private Long maxNoAttempts;
    @Column(name = "isApprovalNeededForRescheduleAtAppointment")
    private Boolean isApprovalNeededForRescheduleAtAppointment;
    @Column(name = "IsApprovalNeedForReschedule")
    private Boolean IsApprovalNeedForReschedule;
    @Column(name = "IsPhotoNeedForReschedule")
    private Boolean IsPhotoNeedForReschedule;
    @Column(name = "maxNoField")
    private Long maxNoField;
    @Column(name = "ticketClose")
    private String ticketClose;
    @Column(name = "BillableReject")
    private String BillableReject;
    @Column(name = "nonBillable")
    private String nonBillable;
    @Column(name = "isInventoryManagementRequired")
    private Boolean isInventoryManagementRequired;
    @Column(name = "isStockKeepingRequired")
    private Boolean isStockKeepingRequired;
    @Column(name = "isConsolidationOrAggregationRequired")
    private Boolean isConsolidationOrAggregationRequired;
    @Column(name = "isClientHubRequired")
    private Boolean isClientHubRequired;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Column(name = "city")
    private String city;
    @Column(name = "pinCode")
    private String pinCode;
    @Column(name = "email")
    private String email;
    @Column(name = "contact")
    private String contact;
    @Column(name = "isPickUpRequired")
    private Boolean isPickUpRequired;
}
