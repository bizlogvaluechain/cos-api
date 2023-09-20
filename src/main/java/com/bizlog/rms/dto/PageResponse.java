package com.bizlog.rms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T extends BaseDTO> {
    Map<String, Object> metaData = new HashMap<>();
    List<T> data = new ArrayList<>();
}
