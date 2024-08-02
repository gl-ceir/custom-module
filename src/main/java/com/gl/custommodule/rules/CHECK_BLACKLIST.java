package com.gl.custommodule.rules;

import com.gl.custommodule.dto.DeviceRegistrationRequest;
import com.gl.custommodule.dto.DeviceRegistrationResponse;
import com.gl.custommodule.enums.ResponseStatusEnum;
import com.gl.custommodule.model.app.BlackList;
import com.gl.custommodule.repository.app.BlackListRepository;
import com.gl.custommodule.service.RuleExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CHECK_BLACKLIST implements RulesInterface {
    @Autowired
    BlackListRepository blackListRepository;
    @Override
    public DeviceRegistrationResponse.ResponseData validate(DeviceRegistrationResponse.ResponseData data, RuleExecutionContext rex, DeviceRegistrationRequest request) {
        if (ResponseStatusEnum.OK.name().equals(data.getStatus())) {
            // logic
            String ImeiPattern = "%" + request.getImei().substring(0, 14) + "%";
            Optional<BlackList> blacklistImeiDetails = blackListRepository.findTopByImeiLike(ImeiPattern);
            if (blacklistImeiDetails.isPresent()) {
                data.setStatus(ResponseStatusEnum.NOT_OK.name());
                data.setReason("IMEI Already existing in CEIR database");
            } else {
                System.out.println("Imei not found in black_list table for imei: "+request.getImei());
            }
        }
        return data;
    }
}
