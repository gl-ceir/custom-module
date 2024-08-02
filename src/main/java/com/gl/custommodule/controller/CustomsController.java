package com.gl.custommodule.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gl.custommodule.dto.DeviceRegistrationRequest;
import com.gl.custommodule.dto.DeviceRegistrationResponse;
import com.gl.custommodule.enums.ResponseStatusEnum;
import com.gl.custommodule.enums.Rules;
import com.gl.custommodule.model.app.*;
import com.gl.custommodule.repository.app.*;
import com.gl.custommodule.rules.*;
import com.gl.custommodule.service.AlertService;
import com.gl.custommodule.service.AuditService;
import com.gl.custommodule.service.RuleExecutionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CustomsController {
    private final Logger log = LogManager.getLogger(getClass());

    @Autowired
    RuleEngineMappingRepository ruleEngineMappingRepository;
    @Autowired
    SystemConfigurationDbRepository systemConfigurationDbRepository;
    @Autowired
    TempWhitelistRepository tempWhitelistRepository;
    @Autowired
    AlertService alertService;
    @Autowired
    AuditService auditService;
    @Autowired
    CustomImeiDbRepository customImeiDbRepository;
    @Autowired
    CustomImeiFailedRequestsRepository customImeiFailedRequestsRepository;
    @Autowired
    CHECK_GLOBAL_BLACKLIST checkGlobalBlacklist;
    @Autowired
    CHECK_CUSTOMS checkCustoms;
    @Autowired
    CHECK_NWL checkNwl;
    @Autowired
    CHECK_STOLEN checkStolen;
    @Autowired
    TYPE_APPROVED typeApproved;
    @Autowired
    VALID_TAC validTac;
    @Autowired
    CHECK_BLACKLIST checkBlacklist;
    @Autowired
    CHECK_GREYLIST checkGreylist;
    @Autowired
    CHECK_EXCEPTIONLIST checkExceptionlist;

    @PostMapping("/registerDevice")
    public ResponseEntity<DeviceRegistrationResponse> registerDevice(@RequestBody DeviceRegistrationRequest request, HttpServletRequest servletRequest) throws JsonProcessingException {
        String userId = servletRequest.getAttribute("X-User-Id").toString();
        Integer moduleAuditId = null;
        try {
            log.info("Received request with data: {}", request.toString());
            String logId = auditService.logAudit("", "Starting processing for imei: "+request.getImei(), "created", "INSERT", 201, 0, 0);
            if (!logId.isEmpty()) {
                moduleAuditId = Integer.parseInt(logId);
            }
            Integer isCustomTaxPaid = null;
            Integer is_used_flag = null;
            if(request.getCustomDutyStatus().equals("0") || request.getCustomDutyStatus().equals("1")) {
                isCustomTaxPaid = Integer.parseInt(request.getCustomDutyStatus());
            }
            if (request.getDeviceType().equalsIgnoreCase("New")) {
                is_used_flag = 0;
            } else if (request.getDeviceType().equalsIgnoreCase("Used")) {
                is_used_flag = 1;
            }
            List<RuleEngineMapping> rules = ruleEngineMappingRepository.getByFeatureAndUserTypeOrderByRuleOrder("customs_api_module", "default", "Enabled");
            String names = rules.stream()
                    .map(RuleEngineMapping::getName)
                    .collect(Collectors.joining(", "));

            log.info("Enabled rules: {}", names);
//            String[] rules = {
//                    "VALID_TAC",
//                    "TYPE_APPROVED",
//                    "CHECK_NWL",
//                    "CHECK_CUSTOMS",
//                    "CHECK_STOLEN",
//                    "CHECK_GLOBAL_BLACKLIST",
//                    "CHECK_BLACKLIST",
//                    "CHECK_GREYLIST",
//                    "CHECK_EXCEPTIONLIST"
//            };
            DeviceRegistrationResponse.ResponseData responseData = new DeviceRegistrationResponse.ResponseData(request.getImei(), ResponseStatusEnum.OK.name(), "");
            RuleExecutionContext context = new RuleExecutionContext();
            for (RuleEngineMapping rule : rules) {
                log.info("Running rule- {} for imei {}", Rules.valueOf(rule.getName().trim()), request.getImei());
//                switch (Rules.valueOf(rule.trim())) {
                switch (Rules.valueOf(rule.getName().trim())) {
                    case CUSTOMS_VALID_TAC:
                        if (moduleAuditId != null) {
                            auditService.updateAudit(moduleAuditId, "", "checking if valid tac","UPDATE", "Running rule", 200, 0, 0);
                        }
                        responseData = validTac.validate(responseData, context, request);
                        break;
                    case TYPE_APPROVED:
                        if (moduleAuditId != null) {
                            auditService.updateAudit(moduleAuditId, "", "checking if type approved","UPDATE", "Running rule", 200, 0, 0);
                        }
                        responseData = typeApproved.validate(responseData, context, request);
                        break;
                    case CHECK_NWL:
                        if (moduleAuditId != null) {
                            auditService.updateAudit(moduleAuditId, "", "checking if present in national whitelist","UPDATE", "Running rule", 200, 0, 0);
                        }
                        responseData = checkNwl.validate(responseData, context, request);
                        break;
                    case CHECK_CUSTOMS:
                        if (moduleAuditId != null) {
                            auditService.updateAudit(moduleAuditId, "", "checking if present in customs imei db","UPDATE", "Running rule", 200, 0, 0);
                        }
                        responseData = checkCustoms.validate(responseData, context, request);
                        break;
                    case CHECK_STOLEN:
                        if (moduleAuditId != null) {
                            auditService.updateAudit(moduleAuditId, "", "checking if present in stolen device detail","UPDATE", "Running rule", 200, 0, 0);
                        }
                        responseData = checkStolen.validate(responseData, context, request);
                        break;
                    case CHECK_GLOBAL_BLACKLIST:
                        if (moduleAuditId != null) {
                            auditService.updateAudit(moduleAuditId, "", "checking if present in blacklist imei details","UPDATE", "Running rule", 200, 0, 0);
                        }
                        responseData = checkGlobalBlacklist.validate(responseData, context, request);
                        break;
                    case CHECK_BLACKLIST:
                        if (moduleAuditId != null) {
                            auditService.updateAudit(moduleAuditId, "", "checking if present in black list","UPDATE", "Running rule", 200, 0, 0);
                        }
                        responseData = checkBlacklist.validate(responseData, context, request);
                        break;
                    case CHECK_GREYLIST:
                        if (moduleAuditId != null) {
                            auditService.updateAudit(moduleAuditId, "", "checking if present in grey list","UPDATE", "Running rule", 200, 0, 0);
                        }
                        responseData = checkGreylist.validate(responseData, context, request);
                        break;
                    case CHECK_EXCEPTIONLIST:
                        if (moduleAuditId != null) {
                            auditService.updateAudit(moduleAuditId, "", "checking if present in exception list","UPDATE", "Running rule", 200, 0, 0);
                        }
                        responseData = checkExceptionlist.validate(responseData, context, request);
                        break;
                }
            }
            if (ResponseStatusEnum.OK.name().equals(responseData.getStatus())) {
                Optional<SystemConfigurationDb> customFlag = Optional.ofNullable(systemConfigurationDbRepository.getByTag("custom_enable_flag"));
                if (customFlag.isPresent()) {
                    if (customFlag.get().getValue().equals("true")) {
                        if (moduleAuditId != null) {
                            auditService.updateAudit(moduleAuditId, "", "checking if present in temp whitelist","UPDATE", "running", 200, 0, 0);
                        }
                        log.info("Checking if imei {} present in temp whitelist", request.getImei());
                        Optional<TempWhitelist> tempWhitelist = tempWhitelistRepository.findByActualImei(request.getImei());
                        if (moduleAuditId != null) {
                            auditService.updateAudit(moduleAuditId, "", "imei found. deleting it from temp whitelist","UPDATE", "running", 200, 0, 0);
                        }
                        tempWhitelist.ifPresent(whitelist -> {
                            log.info("Imei {} found in temp whitelist.", request.getImei());
                            tempWhitelistRepository.deleteById(Long.valueOf(whitelist.getId()));
                        });
                    }
                }
                CustomImeiDb customImei = CustomImeiDb.builder()
                        .imei(request.getImei())
                        .createdOn(LocalDateTime.now())
                        .modifiedOn(LocalDateTime.now())
                        .importerId(request.getImporterId())
                        .importerName(request.getImporterCompanyName())
                        .serialNumber(request.getSerialNumber())
                        .dateOfRegistration(request.getRegistrationDate())
                        .dateOfActualImport(request.getImportDate())
                        .isCustomTaxPaid(isCustomTaxPaid)
                        .isUsedFlag(is_used_flag)
                        .status(1)
                        .reason(responseData.getReason())
                        .build();
                if (moduleAuditId != null) {
                    auditService.updateAudit(moduleAuditId, "", "Saving in custom imei db","UPDATE", "running", 200, 0, 0);
                }
                log.info("Saving imei {} in custom_imei_db", request.getImei());
                customImeiDbRepository.save(customImei);
            } else if (ResponseStatusEnum.NOT_OK.name().equals(responseData.getStatus())) {
                CustomImeiFailedRequests customImei = CustomImeiFailedRequests.builder()
                        .imei(request.getImei())
                        .createdOn(LocalDateTime.now())
                        .modifiedOn(LocalDateTime.now())
                        .importerId(request.getImporterId())
                        .importerName(request.getImporterCompanyName())
                        .serialNumber(request.getSerialNumber())
                        .dateOfRegistration(request.getRegistrationDate())
                        .dateOfActualImport(request.getImportDate())
                        .isCustomTaxPaid(isCustomTaxPaid)
                        .isUsedFlag(is_used_flag)
                        .status(0)
                        .reason(responseData.getReason())
                        .build();
                if (moduleAuditId != null) {
                    auditService.updateAudit(moduleAuditId, "", "Saving in custom imei failed requests","UPDATE", "running", 200, 0, 0);
                }
                log.info("Saving imei {} in custom_imei_failed_request", request.getImei());
                customImeiFailedRequestsRepository.save(customImei);
            }
            int statusCode = HttpStatus.OK.value();
            if (!"".equals(responseData.getReason())) {
                statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            }
            DeviceRegistrationResponse response = new DeviceRegistrationResponse(statusCode, responseData);
            log.info("Response for imei {}: {}", request.getImei(), response.toString());
            if (moduleAuditId != null) {
                auditService.updateAudit(moduleAuditId, "", "Completed processing for imei: "+request.getImei(),"UPDATE", "completed", 200, 1, 0);
            }
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
          e.printStackTrace();
          log.error(e);
          alertService.raiseAlert("alert2162", "", userId );
          if (moduleAuditId == null) {
              auditService.logAudit(Objects.requireNonNull(e.getMessage()).substring(0, 200), "Exception Occurred", "failed", "INSERT", 500, 0, 1);
          } else {
              auditService.updateAudit(moduleAuditId, Objects.requireNonNull(e.getMessage()).substring(0, 200), "Exception Occurred","UPDATE", "failed", 500, 0, 1);
          }
          DeviceRegistrationResponse.ResponseData responseData = new DeviceRegistrationResponse.ResponseData(request.getImei(), ResponseStatusEnum.ERROR.name(), "Data Access Exception. Please try again later.");
          DeviceRegistrationResponse response = new DeviceRegistrationResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseData);
          return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex);
            if (moduleAuditId == null) {
                auditService.logAudit(Objects.requireNonNull(ex.getMessage()).substring(0, 200), "Exception Occurred", "failed", "INSERT", 500, 0, 1);
            } else {
                auditService.updateAudit(moduleAuditId, Objects.requireNonNull(ex.getMessage()).substring(0, 200), "Exception Occurred","UPDATE", "failed", 500, 0, 1);
            }
            alertService.raiseAlert("alert2163", "", userId);
            DeviceRegistrationResponse.ResponseData responseData = new DeviceRegistrationResponse.ResponseData(request.getImei(), ResponseStatusEnum.ERROR.name(), "Exception occurred. Please try again later.");
            DeviceRegistrationResponse response = new DeviceRegistrationResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseData);
            return ResponseEntity.ok(response);
        }
    }
}
