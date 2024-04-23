package com.ps.warehouse.utils;

public enum CommonMessagesEnum {

    VALIDATION_ALREADY_EXISTS("commonmessages.validation.already-exists.message"),
    SOMETHING_WENT_WRONG("commonmessages.something.went.wrong.message"),
    VALIDATION_FAIL("commonmessages.validation.fail.message"),
    VALIDATION_NEGATIVE_AMOUNT("commonmessages.validation.negative.amount.message"),
    VALIDATION_DUPLICATED_CURRENCY_CODES("commonmessages.validation.duplicated.currency.codes");

    private final String key;

    CommonMessagesEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
