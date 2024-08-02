package com.gl.custommodule.rules;

import com.gl.custommodule.dto.DeviceRegistrationRequest;
import com.gl.custommodule.dto.DeviceRegistrationResponse;
import com.gl.custommodule.service.RuleExecutionContext;

public interface RulesInterface {
    DeviceRegistrationResponse.ResponseData validate(DeviceRegistrationResponse.ResponseData data, RuleExecutionContext rex, DeviceRegistrationRequest request);
}
