package com.gl.custommodule.model.app;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TEMP_NATIONAL_WHITELIST", schema = "APP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TempWhitelist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;

    @Column(name = "MODIFIED_ON")
    private LocalDateTime modifiedOn;

    @Column(name = "FOREIGN_RULE")
    private String foreignRule;

    @Column(name = "MOBILE_OPERATOR")
    private String mobileOperator;

    @Column(name = "PERIOD")
    private String period;

    @Column(name = "TAX_PAID")
    private String taxPaid;

    @Column(name = "CREATED_FILENAME")
    private String createdFilename;

    @Column(name = "UPDATED_FILENAME")
    private String updatedFilename;

    @Column(name = "UPDATED_ON")
    private LocalDateTime updatedOn;

    @Column(name = "SYSTEM_TYPE")
    private String systemType;

    @Column(name = "FAILED_RULE_ID")
    private Long failedRuleId;

    @Column(name = "FAILED_RULE_NAME")
    private String failedRuleName;

    @Column(name = "VALIDITY_FLAG")
    private Integer validityFlag;

    @Column(name = "TAC")
    private String tac;

    @Column(name = "ACTION")
    private String action;

    @Column(name = "FAILED_RULE_DATE")
    private LocalDateTime failedRuleDate;

    @Column(name = "FEATURE_NAME")
    private String featureName;

    @Column(name = "RECORD_TIME")
    private LocalDateTime recordTime;

    @Column(name = "ACTUAL_IMEI")
    private String actualImei;

    @Column(name = "RECORD_TYPE")
    private String recordType;

    @Column(name = "IMEI")
    private String imei;

    @Column(name = "RAW_CDR_FILE_NAME")
    private String rawCdrFileName;

    @Column(name = "IMEI_ARRIVAL_TIME")
    private LocalDateTime imeiArrivalTime;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "UPDATE_RAW_CDR_FILE_NAME")
    private String updateRawCdrFileName;

    @Column(name = "UPDATE_IMEI_ARRIVAL_TIME")
    private LocalDateTime updateImeiArrivalTime;

    @Column(name = "UPDATE_SOURCE")
    private String updateSource;

    @Column(name = "SERVER_ORIGIN")
    private String serverOrigin;

    @Column(name = "LIST_TYPE")
    private String listType;

    @Column(name = "TYPE_OF_ENTRY")
    private String typeOfEntry;

    @Column(name = "NATIONAL_WHITE_LIST_CREATED_DATE")
    private LocalDateTime nationalWhiteListCreatedDate;

    @Column(name = "REASON")
    private String reason;

    @Column(name = "IMSI")
    private String imsi;

    @Column(name = "MSISDN")
    private String msisdn;

    @Column(name = "CREATED_ON_DATE")
    private java.time.LocalDate createdOnDate;

    @Column(name = "DEVICE_TYPE")
    private String deviceType;

    @Column(name = "ACTUAL_OPERATOR")
    private String actualOperator;

    @Column(name = "IS_TEST_IMEI")
    private String isTestImei;

    @Column(name = "IS_USED_DEVICE_IMEI")
    private String isUsedDeviceImei;

    @Column(name = "REASON_FOR_INVALID_IMEI")
    private String reasonForInvalidImei;

}
