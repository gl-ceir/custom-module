package com.gl.custommodule.service;

import java.util.HashMap;
import java.util.Map;

public class RuleExecutionContext {
    private Map<String, Object> sharedData = new HashMap<>();

    public void setSharedData(String key, Object value) {
        sharedData.put(key, value);
    }

    public Object getSharedData(String key) {
        return sharedData.get(key);
    }

    public boolean hasKey(String key) {
        return sharedData.containsKey(key);
    }
}

