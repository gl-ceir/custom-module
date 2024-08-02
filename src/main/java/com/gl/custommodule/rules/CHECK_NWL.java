package com.gl.custommodule.rules;

import com.gl.custommodule.dto.DeviceRegistrationRequest;
import com.gl.custommodule.dto.DeviceRegistrationResponse;
import com.gl.custommodule.enums.ResponseStatusEnum;
import com.gl.custommodule.model.app.NationalWhitelist;
import com.gl.custommodule.repository.app.NationalWhitelistRepository;
import com.gl.custommodule.service.RuleExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CHECK_NWL implements RulesInterface {
    @Autowired
    NationalWhitelistRepository nationalWhitelistRepository;

    @Override
    public DeviceRegistrationResponse.ResponseData validate(DeviceRegistrationResponse.ResponseData data, RuleExecutionContext rex, DeviceRegistrationRequest request) {
        if (ResponseStatusEnum.OK.name().equals(data.getStatus())) {
            // logic
            Optional<NationalWhitelist> nwl = nationalWhitelistRepository.findByImei(request.getImei().substring(0, 14));
            if (nwl.isPresent()) {
                data.setStatus(ResponseStatusEnum.NOT_OK.name());
                data.setReason("IMEI Already exists in CEIR Database");
            } else {
                System.out.println("Imei not found in national_whitelist table for imei: "+request.getImei());
            }
        }
        return data;
    }
}
