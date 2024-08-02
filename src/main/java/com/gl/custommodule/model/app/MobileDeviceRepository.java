package com.gl.custommodule.model.app;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mobile_device_repository")
@Data
public class MobileDeviceRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "allocation_date")
    private LocalDateTime allocationDate;

    @Column(name = "announce_date")
    private LocalDateTime announceDate;

    @Column(name = "band_detail", length = 50)
    private String bandDetail;

    @Column(name = "battery_capacity")
    private Integer batteryCapacity;

    @Column(name = "battery_charging", length = 100)
    private String batteryCharging;

    @Column(name = "battery_type", length = 50)
    private String batteryType;

    @Column(name = "body_dimension", length = 50)
    private String bodyDimension;

    @Column(name = "body_weight", length = 20)
    private String bodyWeight;

    @Column(name = "brand_name", length = 50)
    private String brandName;

    @Column(name = "color", length = 100)
    private String color;

    @Column(name = "comms_bluetooth", length = 100)
    private String commsBluetooth;

    @Column(name = "comms_gps", length = 100)
    private String commsGps;

    @Column(name = "comms_nfc")
    private Integer commsNfc;

    @Column(name = "comms_radio")
    private Integer commsRadio;

    @Column(name = "comms_usb", length = 50)
    private String commsUsb;

    @Column(name = "comms_wlan", length = 100)
    private String commsWlan;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "custom_price_of_device")
    private Double customPriceOfDevice;

    @Column(name = "device_id", length = 8, unique = true)
    private String deviceId;

    @Column(name = "device_state")
    private Integer deviceState;

    @Column(name = "device_status", length = 20)
    private String deviceStatus;

    @Column(name = "device_type", length = 50)
    private String deviceType;

    @Column(name = "discontinue_date")
    private LocalDateTime discontinueDate;

    @Column(name = "display_protection", length = 50)
    private String displayProtection;

    @Column(name = "display_resolution", length = 50)
    private String displayResolution;

    @Column(name = "display_size", length = 50)
    private String displaySize;

    @Column(name = "display_type", length = 50)
    private String displayType;

    @Column(name = "esim_support")
    private Integer esimSupport;

    @Column(name = "imei_quantity")
    private Integer imeiQuantity;

    @Column(name = "launch_date")
    private LocalDateTime launchDate;

    @Column(name = "launch_price_asian_market")
    private Double launchPriceAsianMarket;

    @Column(name = "launch_price_cambodia_market")
    private Double launchPriceCambodiaMarket;

    @Column(name = "launch_price_europe_market")
    private Double launchPriceEuropeMarket;

    @Column(name = "launch_price_international_market")
    private Double launchPriceInternationalMarket;

    @Column(name = "launch_price_us_market")
    private Double launchPriceUsMarket;

    @Column(name = "main_camera_feature", length = 50)
    private String mainCameraFeature;

    @Column(name = "main_camera_spec", length = 50)
    private String mainCameraSpec;

    @Column(name = "main_camera_type")
    private Integer mainCameraType;

    @Column(name = "main_camera_video", length = 50)
    private String mainCameraVideo;

    @Column(name = "manufacturer", length = 100)
    private String manufacturer;

    @Column(name = "manufacturing_location", length = 100)
    private String manufacturingLocation;

    @Column(name = "marketing_name", length = 100)
    private String marketingName;

    @Column(name = "memory_card_slot")
    private Integer memoryCardSlot;

    @Column(name = "memory_internal")
    private Integer memoryInternal;

    @Column(name = "model_name", length = 50)
    private String modelName;

    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Column(name = "network_2g_band", length = 100)
    private String network2gBand;

    @Column(name = "network_3g_band", length = 100)
    private String network3gBand;

    @Column(name = "network_4g_band", length = 100)
    private String network4gBand;

    @Column(name = "network_5g_band", length = 100)
    private String network5gBand;

    @Column(name = "network_6g_band", length = 100)
    private String network6gBand;

    @Column(name = "network_7g_band", length = 100)
    private String network7gBand;

    @Column(name = "network_speed", length = 100)
    private String networkSpeed;

    @Column(name = "network_technology_5g")
    private Integer networkTechnology5g;

    @Column(name = "network_technology_6g")
    private Integer networkTechnology6g;

    @Column(name = "network_technology_7g")
    private Integer networkTechnology7g;

    @Column(name = "network_technology_cdma")
    private Integer networkTechnologyCdma;

    @Column(name = "network_technology_evdo")
    private Integer networkTechnologyEvdo;

    @Column(name = "network_technology_gsm")
    private Integer networkTechnologyGsm;

    @Column(name = "network_technology_lte")
    private Integer networkTechnologyLte;

    @Column(name = "nonremovable_euicc")
    private Integer nonremovableEuicc;

    @Column(name = "nonremovable_uicc")
    private Integer nonremovableUicc;

    @Column(name = "oem", length = 100)
    private String oem;

    @Column(name = "organization_id", length = 25)
    private String organizationId;

    @Column(name = "os", length = 50)
    private String os;

    @Column(name = "os_base_version", length = 50)
    private String osBaseVersion;

    @Column(name = "platform_cpu", length = 100)
    private String platformCpu;

    @Column(name = "platform_chipset", length = 100)
    private String platformChipset;

    @Column(name = "platform_gpu", length = 50)
    private String platformGpu;

    @Column(name = "ram", length = 20)
    private String ram;

    @Column(name = "remark", length = 1000)
    private String remark;

    @Column(name = "removable_euicc")
    private Integer removableEuicc;

    @Column(name = "removable_uicc")
    private Integer removableUicc;

    @Column(name = "reported_global_issue", length = 255)
    private String reportedGlobalIssue;

    @Column(name = "reported_local_issue", length = 255)
    private String reportedLocalIssue;

    @Column(name = "selfie_camera_feature", length = 50)
    private String selfieCameraFeature;

    @Column(name = "selfie_camera_spec", length = 50)
    private String selfieCameraSpec;

    @Column(name = "selfie_camera_type")
    private Integer selfieCameraType;

    @Column(name = "selfie_camera_video", length = 50)
    private String selfieCameraVideo;

    @Column(name = "sensor", length = 50)
    private String sensor;

    @Column(name = "sim_slot")
    private Integer simSlot;

    @Column(name = "sim_type", length = 15)
    private String simType;

    @Column(name = "softsim_support")
    private Integer softsimSupport;

    @Column(name = "sound_3_5mm_jack")
    private Integer sound35mmJack;

    @Column(name = "sound_loudspeaker")
    private Integer soundLoudspeaker;

    @Column(name = "source_of_cambodia_market_price", length = 100)
    private String sourceOfCambodiaMarketPrice;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "is_test_imei")
    private Integer isTestImei;

    @Column(name = "os_current_version", length = 50)
    private String osCurrentVersion;

    @Column(name = "network_specific_identifier")
    private Integer networkSpecificIdentifier;

    @Column(name = "is_type_approved")
    private Integer isTypeApproved;
}
