package com.ps.warehouse.utils;

public enum CommonMessagesEnum {

    VALIDATION_ALREADY_EXISTS("commonmessages.validation.already-exists.message"),
    GENERIC_ERROR("commonmessages.generic.error.message"),
    VALIDATION_FAIL("commonmessages.validation.fail.message");

    private final String key;

    CommonMessagesEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getKeyWithExtraBrackets() {
        return "{" + key + "}";
    }

}
