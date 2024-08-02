package com.gl.custommodule.model.app;

import javax.persistence.*;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@Entity
@Table(name = "blacklist_imei_details")
public class BlacklistImeiDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bluetooth;

    private String brandName;

    private String deviceType;

    private String manufacturer;

    private String marketingName;

    private String modelName;

    private String nfc;

    private String operatingSys;

    private String wlan;

    private Long blacklistTacDbId;

    private LocalDateTime createdOn;

    private LocalDateTime modifiedOn;

    private String refCode;

    private String responseStatus;

    private String deviceId;

    private String partnerId;

    private String blocklistStatus;

    private String generallistStatus;

    private String band;
}

