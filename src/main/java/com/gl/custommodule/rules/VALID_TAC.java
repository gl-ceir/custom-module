package com.gl.custommodule.rules;

import com.gl.custommodule.dto.DeviceRegistrationRequest;
import com.gl.custommodule.dto.DeviceRegistrationResponse;
import com.gl.custommodule.enums.ResponseStatusEnum;
import com.gl.custommodule.model.app.MobileDeviceRepository;
import com.gl.custommodule.repository.app.MobileDeviceRepoRepository;
import com.gl.custommodule.service.RuleExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VALID_TAC implements RulesInterface {
    @Autowired
    MobileDeviceRepoRepository mobileDeviceRepository;
    @Override
    public DeviceRegistrationResponse.ResponseData validate(DeviceRegistrationResponse.ResponseData data, RuleExecutionContext rex, DeviceRegistrationRequest request) {
        if (ResponseStatusEnum.OK.name().equals(data.getStatus())) {
            // logic
            String mobileDeviceKey = "mobileDeviceKey";
            MobileDeviceRepository mobileDevice = null;
            if (rex.hasKey(mobileDeviceKey)) {
                mobileDevice = (MobileDeviceRepository) rex.getSharedData(mobileDeviceKey);
                if (mobileDevice == null) {
                    data.setStatus(ResponseStatusEnum.NOT_OK.name());
                    data.setReason("Non GSMA Compliant IMEI");
                }
            } else {
                Optional<MobileDeviceRepository> result = mobileDeviceRepository.findByDeviceId(request.getImei().substring(0, 8));
                if (result.isPresent()) {
                    mobileDevice = result.get();
                    rex.setSharedData(mobileDeviceKey, mobileDevice);
                    System.out.println("Device id found in mobile_device_repository table for imei: "+request.getImei());
                } else {
                    data.setStatus(ResponseStatusEnum.NOT_OK.name());
                    data.setReason("Non GSMA Compliant IMEI");
                }
            }
        }
        return data;
    }
}
