package com.bizlog.rms.dto.Specifications;

import com.bizlog.rms.dto.BaseDTO;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TATActivityDTO extends BaseDTO {
    @ElementCollection
    @NotEmpty(message = "intraCity should not be empty")
    private List<String> intraCity;
    @ElementCollection
    @NotEmpty(message = "outOfDelivery should not be empty")
    private  List<String> outOfDelivery;
    @ElementCollection
    @NotEmpty(message = "nonServicibleArea should not be empty")
    private List<String> nonServicibleArea;

}
