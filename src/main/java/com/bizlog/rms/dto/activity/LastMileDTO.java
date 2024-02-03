package com.bizlog.rms.dto.activity;

import com.bizlog.rms.dto.BaseDTO;
import lombok.Data;

import java.util.List;
@Data
public class LastMileDTO extends BaseDTO {
    private String activityType;
    private Boolean isImageRequired;
    private Boolean isBizlogQuestion;
    private List<String> bizlogQuestion;
    private Boolean isSelectQuestion;
    private List<String> selectQuestion;
    private Boolean isAddQuestion;
    private List<String> addQuestion;
    private Boolean isOtpValidation;
    private List<String> otpValidation;
    private Boolean isSegregation;
    private String segregationCategory;
    private String segregationType;
    private String segregationCriteria;
    private Boolean isSignatureRequired;
    private Boolean isAppointmentRequired;
    private Long maxNoAttempts;
    private Boolean isApprovalNeededForRescheduleAtAppointment;
    private Boolean IsApprovalNeedForReschedule;
    private Boolean IsPhotoNeedForReschedule;
    private Long maxNoField;
    private String ticketClose;
    private String BillableReject;
    private String nonBillable;
    private Boolean isInventoryManagementRequired;
    private Boolean isStockKeepingRequired;
    private Boolean isConsolidationOrAggregationRequired;
    private Boolean isClientHubRequired;
    private String address1;
    private String address2;
    private String city;
    private String pinCode;
    private String email;
    private String contact;
    private Boolean isPickUpRequired;
}
