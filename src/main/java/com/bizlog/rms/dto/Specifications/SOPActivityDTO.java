package com.bizlog.rms.dto.Specifications;

import com.bizlog.rms.dto.BaseDTO;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SOPActivityDTO extends BaseDTO {

    @ElementCollection
    @NotEmpty(message = "activityStartDate should not be empty")
    private List<Date> activityStartDate;

    @ElementCollection
    @NotEmpty(message = "activityEndDate should not be empty")
    private List<Date> activityEndDate;

    @ElementCollection
    @NotEmpty(message = "activityDetail should not be empty")
    private List<String> activityDetail;

    @ElementCollection
    @NotEmpty(message = "volumeOfTicketsPerSowSop should not be empty")
    private List<String> volumeOfTicketsPerSowSop;

    @ElementCollection
    @NotEmpty(message = "volumeOfProductsPerSowSop should not be empty")
    private List<String> volumeOfProductsPerSowSop;

    @NotNull
    private Boolean operationsNeededOnOurHolidays;

    @NotNull
    private Boolean operationsNeededOnYourHolidays;

    @NotNull
    private Boolean operationsNeededOnStatutoryHolidays;
}
