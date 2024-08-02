package com.gl.custommodule.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.custommodule.dto.AuditDTO;
import com.gl.custommodule.dto.builder.AuditBuilder;
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
public class AuditService {
    private final Logger log = LogManager.getLogger(getClass());

    @Value("${audit.api.url}")
    private String auditApiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public AuditService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String logAudit(String errorMessage, String info, String status, String action, Integer statusCode, Integer count, Integer failureCount) {
        AuditDTO auditDTO = AuditBuilder.createAudit(errorMessage, info, action, status, statusCode, count, failureCount);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<AuditDTO> entity = new HttpEntity<>(auditDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                auditApiUrl+"/moduleAuditServiceInsert", HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());
                JsonNode dataNode = root.path("data");
                if (!dataNode.isMissingNode()) {
                    return dataNode.asText();
                }
            } catch (Exception e) {
                log.error("Failed to parse the audit log response", e);
            }
        } else {
            log.error("Audit log failed with response: " + response.getBody());
        }
        return null;
    }

    public void updateAudit(Integer id, String errorMessage, String info, String action, String status, Integer statusCode, Integer count, Integer failureCount) throws JsonProcessingException {
        AuditDTO auditDTO = AuditBuilder.createAuditForUpdate(id, errorMessage, info, action, status, statusCode, count, failureCount);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<AuditDTO> entity = new HttpEntity<>(auditDTO, headers);

//        // Serialize auditDTO to JSON for printing
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonPayload = objectMapper.writeValueAsString(auditDTO);
//
//        // Construct and print the curl command
//        String curlCommand = String.format("curl --location --request PUT '%s/moduleAuditServiceUpdate' " +
//                        "--header 'Content-Type: application/json' " +
//                        "--data-raw '%s'",
//                auditApiUrl, jsonPayload.replace("'", "\\'"));
//
//        System.out.println("Executing request: " + curlCommand);

        ResponseEntity<String> response = restTemplate.exchange(
                auditApiUrl + "/moduleAuditServiceUpdate", HttpMethod.PUT, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Audit log updated with response: " + response.getBody());
        } else {
            log.error("Audit log update failed with response: " + response.getBody());
        }
    }
}

