package com.gl.custommodule.rules;

import com.gl.custommodule.dto.DeviceRegistrationRequest;
import com.gl.custommodule.dto.DeviceRegistrationResponse;
import com.gl.custommodule.enums.ResponseStatusEnum;
import com.gl.custommodule.model.app.StolenDeviceDetail;
import com.gl.custommodule.repository.app.StolenDeviceDetailRepository;
import com.gl.custommodule.service.RuleExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CHECK_STOLEN implements RulesInterface {
    @Autowired
    StolenDeviceDetailRepository stolenDeviceDetailRepository;

    @Override
    public DeviceRegistrationResponse.ResponseData validate(DeviceRegistrationResponse.ResponseData data, RuleExecutionContext rex, DeviceRegistrationRequest request) {
        if (ResponseStatusEnum.OK.name().equals(data.getStatus())) {
            // logic
            Optional<StolenDeviceDetail> deviceDetailOptional = stolenDeviceDetailRepository.findByImei(request.getImei());
            if (deviceDetailOptional.isPresent()) {
                data.setStatus(ResponseStatusEnum.NOT_OK.name());
                data.setReason("IMEI already in use and stolen");
            } else {
                System.out.println("Imei not found in stolen_device_detail table for imei: "+request.getImei());
            }

        }
        return data;
    }
}
