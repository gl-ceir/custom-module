package com.gl.custommodule.service;

import com.gl.custommodule.dto.AlertDTO;
import com.gl.custommodule.dto.builder.AlertBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlertService {
    private final Logger log = LogManager.getLogger(getClass());

    @Value("${alert.api.url}")
    private String alertApiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public AlertService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void raiseAlert(String alertId, String alertMessage, String userId) {
        // Use AlertBuilder to create AlertDTO object
        AlertDTO alertDTO = AlertBuilder.createAlert(alertId, alertMessage, userId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        // headers.set("Authorization", "Bearer your_token_here");

        HttpEntity<AlertDTO> entity = new HttpEntity<>(alertDTO, headers);

        // Assuming the API response is of type String. Change this as per your needs.
        ResponseEntity<String> response = restTemplate.exchange(
                alertApiUrl + "/alert", HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Alert raised with response: " + response.getBody());
        } else {
            log.error("Failed to raise alert with response: " + response.getBody());
        }
    }
}

