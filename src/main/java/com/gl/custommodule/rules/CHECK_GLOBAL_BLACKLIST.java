package com.gl.custommodule.rules;

import com.gl.custommodule.dto.DeviceRegistrationRequest;
import com.gl.custommodule.dto.DeviceRegistrationResponse;
import com.gl.custommodule.enums.ResponseStatusEnum;
import com.gl.custommodule.model.app.BlacklistImeiDetails;
import com.gl.custommodule.model.app.SystemConfigurationDb;
import com.gl.custommodule.model.app.TempWhitelist;
import com.gl.custommodule.repository.app.BlacklistImeiDetailsRepository;
import com.gl.custommodule.repository.app.SystemConfigurationDbRepository;
import com.gl.custommodule.service.RuleExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CHECK_GLOBAL_BLACKLIST implements RulesInterface {
    @Autowired
    SystemConfigurationDbRepository systemConfigurationDbRepository;
    @Autowired
    BlacklistImeiDetailsRepository blacklistImeiDetailsRepository;

    @Override
    public DeviceRegistrationResponse.ResponseData validate(DeviceRegistrationResponse.ResponseData data, RuleExecutionContext rex, DeviceRegistrationRequest request) {
        Optional<SystemConfigurationDb> customFlag = Optional.ofNullable(systemConfigurationDbRepository.getByTag("gsma_blacklist_enable_flag"));
        if (customFlag.isPresent()) {
            if (customFlag.get().getValue().equals("true")) {
                if (ResponseStatusEnum.OK.name().equals(data.getStatus())) {
                    // logic
                    String deviceIdPattern = "%" + request.getImei().substring(0, 14) + "%";
                    Optional<BlacklistImeiDetails> blacklistImeiDetails = blacklistImeiDetailsRepository.findTopByDeviceIdLike(deviceIdPattern);
                    if (blacklistImeiDetails.isPresent()) {
                        data.setStatus(ResponseStatusEnum.NOT_OK.name());
                        data.setReason("IMEI Already existing in Global Blacklist");
                    } else {
                        System.out.println("Imei not found in blacklist_imei_details table for imei: "+request.getImei());
                    }
                }
            }
        }
        return data;
    }
}
