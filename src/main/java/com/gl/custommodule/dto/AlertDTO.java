package com.gl.custommodule.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AlertDTO {
    private String alertId;
    private String alertMessage;
    private String alertProcess;
    private String description;
    private String featureName;
    private String ip;
    private String priority;
    private String remarks;
    private String serverName;
    private Integer status;
    private String txnId;
    private Integer userId;
}
