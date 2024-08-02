package com.gl.custommodule.dto.builder;

import com.gl.custommodule.dto.AuditDTO;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AuditBuilder {

    public static AuditDTO createAudit(
            String errorMessage,
            String info,
            String action,
            String status,
            Integer statusCode,
            Integer count,
            Integer failureCount) {
        String serverName;
        try {
            serverName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            serverName = "UnknownHost";
        }

        return AuditDTO.builder()
                .errorMessage(errorMessage)
                .info(info)
                .action(action)
                .status(status)
                .statusCode(statusCode)
                .count(count)
                .failureCount(failureCount)
                .executionTime(Math.toIntExact(System.currentTimeMillis() / 1000))
                .featureName("Customs API")
                .moduleName("Device Registration Module")
                .serverName(serverName)
                .build();
    }

    public static AuditDTO createAuditForUpdate(Integer id, String errorMessage, String info, String action, String status, Integer statusCode, Integer count, Integer failureCount) {
        String serverName;
        try {
            serverName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            serverName = "UnknownHost";
        }

        return AuditDTO.builder()
                .id(id)
                .errorMessage(errorMessage)
                .info(info)
                .action(action)
                .status(status)
                .statusCode(statusCode)
                .count(count)
                .failureCount(failureCount)
                .executionTime(Math.toIntExact(System.currentTimeMillis() / 1000))
                .featureName("Customs API")
                .moduleName("Device Registration Module")
                .serverName(serverName)
                .build();
    }
}

