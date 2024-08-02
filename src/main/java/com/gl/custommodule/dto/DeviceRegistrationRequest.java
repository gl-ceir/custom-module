package com.gl.custommodule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DeviceRegistrationRequest {
    private String importerId;
    private String importerCompanyName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime registrationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime importDate;
    private String deviceType;
    private String customDutyStatus;
    private String imei;
    private String serialNumber;
}

