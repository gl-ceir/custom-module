package com.gl.custommodule.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditDTO {
    private Integer id;
    private Integer count;
    private String errorMessage;
    private Integer executionTime;
    private Integer failureCount;
    private String featureName;
    private String info;
    private String action;
    private String moduleName;
    private String serverName;
    private String status;
    private Integer statusCode;
}

