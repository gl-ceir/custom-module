package com.gl.custommodule.rules;

import com.gl.custommodule.dto.DeviceRegistrationRequest;
import com.gl.custommodule.dto.DeviceRegistrationResponse;
import com.gl.custommodule.enums.ResponseStatusEnum;
import com.gl.custommodule.model.app.CustomImeiDb;
import com.gl.custommodule.repository.app.CustomImeiDbRepository;
import com.gl.custommodule.service.RuleExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CHECK_CUSTOMS implements RulesInterface {
    @Autowired
    CustomImeiDbRepository customImeiDbRepository;

    @Override
    public DeviceRegistrationResponse.ResponseData validate(DeviceRegistrationResponse.ResponseData data, RuleExecutionContext rex, DeviceRegistrationRequest request) {
        if (ResponseStatusEnum.OK.name().equals(data.getStatus())) {
            // logic
            Optional<CustomImeiDb> customImeiDb = customImeiDbRepository.findTopByImei(request.getImei());
            if (customImeiDb.isPresent()) {
                data.setStatus(ResponseStatusEnum.NOT_OK.name());
                data.setReason("IMEI Already existing in Custom database");
            } else {
                System.out.println("Imei not found in custom_imei_db table for imei: "+request.getImei());
            }
        }
        return data;
    }
}
