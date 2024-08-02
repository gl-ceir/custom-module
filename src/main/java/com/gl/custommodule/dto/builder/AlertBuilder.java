package com.gl.custommodule.dto.builder;

import com.gl.custommodule.dto.AlertDTO;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class AlertBuilder {

    public static AlertDTO createAlert(String alertId, String alertMessage, String userId) {
        String ip;
        String serverName;
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            ip = localHost.getHostAddress(); // Fetches the IP address
            serverName = localHost.getHostName(); // Fetches the host name
        } catch (UnknownHostException e) {
            ip = "127.0.0.1"; // Default IP if the host IP cannot be determined
            serverName = "UnknownHost"; // Default server name if the host name cannot be determined
        }

        return AlertDTO.builder()
                .alertId(alertId)
                .alertMessage(alertMessage)
                .alertProcess("Device Registration Module")
                .description("")
                .featureName("Customs API")
                .ip(ip)
                .priority("High")
                .remarks("")
                .serverName(serverName)
                .status(0)
                .txnId(UUID.randomUUID().toString())
                .userId(Integer.parseInt(userId))
                .build();
    }
}

