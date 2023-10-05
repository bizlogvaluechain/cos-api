package com.bizlog.rms.dto.Specifications;

import com.bizlog.rms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TATActivityDTO extends BaseDTO {
    private List<TATSpecificationDTO> intraCity;
    private  List<TATSpecificationDTO> outOfDelivery;
    private List<TATSpecificationDTO> nonServicibleArea;

}
