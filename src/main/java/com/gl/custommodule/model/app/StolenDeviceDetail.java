package com.gl.custommodule.model.app;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stolen_device_detail")
@Data
public class StolenDeviceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "imei", unique = true, nullable = false, length = 20)
    private String imei;

    @Column(name = "request_id", nullable = false, length = 20)
    private String requestId;

    @Column(name = "status")
    private Integer status;

    @Column(name = "request_type", length = 2)
    private String requestType;

}
