package com.gl.custommodule.rules;

import com.gl.custommodule.dto.DeviceRegistrationRequest;
import com.gl.custommodule.dto.DeviceRegistrationResponse;
import com.gl.custommodule.enums.ResponseStatusEnum;
import com.gl.custommodule.model.app.MobileDeviceRepository;
import com.gl.custommodule.model.app.SystemConfigurationDb;
import com.gl.custommodule.repository.app.MobileDeviceRepoRepository;
import com.gl.custommodule.repository.app.SystemConfigurationDbRepository;
import com.gl.custommodule.service.RuleExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TYPE_APPROVED implements RulesInterface {
    @Autowired
    SystemConfigurationDbRepository systemConfigurationDbRepository;
    @Autowired
    MobileDeviceRepoRepository mobileDeviceRepoRepository;

    @Override
    public DeviceRegistrationResponse.ResponseData validate(DeviceRegistrationResponse.ResponseData data, RuleExecutionContext rex, DeviceRegistrationRequest request) {
        boolean isTypeApprovedEnabled = false;
        Optional<SystemConfigurationDb> typeApprovedEnabledConfig = Optional.ofNullable(systemConfigurationDbRepository.getByTag("type_approved_enable_flag"));
        if (typeApprovedEnabledConfig.isPresent()) {
            if ("true".equalsIgnoreCase(typeApprovedEnabledConfig.get().getValue())) {
                isTypeApprovedEnabled = true;
            }
        }
        if(isTypeApprovedEnabled) {
            if (ResponseStatusEnum.OK.name().equals(data.getStatus())) {
                // logic
                String mobileDeviceKey = "mobileDeviceKey";
                MobileDeviceRepository mobileDevice = null;
                if (rex.hasKey(mobileDeviceKey)) {
                    mobileDevice = (MobileDeviceRepository) rex.getSharedData(mobileDeviceKey);
                } else {
                    Optional<MobileDeviceRepository> result = mobileDeviceRepoRepository.findByDeviceId(request.getImei().substring(0, 8));
                    if (result.isPresent()) {
                        mobileDevice = result.get();
                        rex.setSharedData(mobileDeviceKey, mobileDevice);
                    } else {
                        data.setStatus(ResponseStatusEnum.NOT_OK.name());
                        data.setReason("Non GSMA Compliant IMEI");
                    }
                }
                if (mobileDevice != null && mobileDevice.getIsTypeApproved() == 0) {
                    data.setStatus(ResponseStatusEnum.NOT_OK.name());
                    data.setReason("Model is not Type Approved");
                } else {
                    System.out.println("is_type_approved: True found in mobile_device_repository table for imei: "+request.getImei());
                }
            }
        }
        return data;
    }
}
