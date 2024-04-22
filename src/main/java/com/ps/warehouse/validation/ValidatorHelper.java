package com.ps.warehouse.validation;

import jakarta.validation.ConstraintValidatorContext;

public class ValidatorHelper {

    private ValidatorHelper() {
    }

    public static void addFieldConstraintViolation(ConstraintValidatorContext context, String msgTemplate, String fieldName) {
        context.buildConstraintViolationWithTemplate(msgTemplate)
               .addPropertyNode(fieldName)
               .addConstraintViolation();
    }
}
