package com.gl.custommodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRegistrationResponse {
    private Integer statusCode;
    private ResponseData data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseData {
        private String imei;
        private String status;
        private String reason;
    }
}

