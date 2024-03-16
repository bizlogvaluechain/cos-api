package com.bizlog.rms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogsDTO {
    private Long entityId;
    private String entity;
    private String revisionId;
    private String timeStamp;
    private String operation;
    private String userName;
}
