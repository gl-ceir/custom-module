package com.gl.custommodule.model.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "custom_imei_failed_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomImeiFailedRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "importer_id", length = 25)
    private String importerId;

    @Column(name = "importer_name", length = 50)
    private String importerName;

    @Column(name = "imei", length = 20)
    private String imei;

    @Column(name = "serial_number", length = 15)
    private String serialNumber;

    @Column(name = "date_of_registration")
    private LocalDateTime dateOfRegistration;

    @Column(name = "date_of_actual_import")
    private LocalDateTime dateOfActualImport;

    @Column(name = "is_used_flag")
    private Integer isUsedFlag;

    @Column(name = "is_custom_tax_paid")
    private Integer isCustomTaxPaid;

    @Column(name = "status")
    private Integer status;

    @Column(name = "reason", length = 100)
    private String reason;
}


