package com.gl.custommodule.model.app;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.time.LocalDateTime;

@Entity
@Table(name = "GREY_LIST", schema = "APP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GreyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TAC", length = 10)
    private String tac;

    @Column(name = "IMEI", length = 20)
    private String imei;

    @Column(name = "ACTUAL_IMEI", length = 20)
    private String actualImei;

    @Column(name = "IMSI", length = 20)
    private String imsi;

    @Column(name = "MSISDN", length = 20)
    private String msisdn;

    @Column(name = "OPERATOR_ID", length = 20)
    private String operatorId;

    @Column(name = "OPERATOR_NAME", length = 20)
    private String operatorName;

    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;

    @Column(name = "MODIFIED_ON")
    private LocalDateTime modifiedOn;

    @Column(name = "COMPLAINT_TYPE", length = 50)
    private String complaintType;

    @Column(name = "REQUEST_TYPE", length = 50)
    private String requestType;

    @Column(name = "TXN_ID", length = 50)
    private String txnId;

    @Column(name = "MODE_TYPE", length = 50)
    private String modeType;

    @Column(name = "USER_TYPE", length = 50)
    private String userType;

    @Column(name = "USER_ID", length = 50)
    private String userId;

    @Column(name = "EXPIRY_DATE")
    private LocalDateTime expiryDate;

    @Column(name = "TAX_PAID")
    private Long taxPaid;

    @Column(name = "CLARIFY_REASON", length = 200)
    private String clarifyReason;

    @Column(name = "REASON", length = 50)
    private String reason;

    @Column(name = "SOURCE_OF_REQUEST", length = 50)
    private String sourceOfRequest;

    @Column(name = "REMARKS", length = 250)
    private String remarks;

}
