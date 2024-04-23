package com.ps.warehouse.validation.validators;

import com.ps.warehouse.dtos.DealDetail;
import com.ps.warehouse.utils.CommonMessagesEnum;
import com.ps.warehouse.validation.ValidatorHelper;
import com.ps.warehouse.validation.annotations.ValidDealDetail;
import com.ps.warehouse.validation.service.DealDetailValidationService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class DealDetailValidator implements ConstraintValidator<ValidDealDetail, DealDetail> {

    public static final String ID_FIELD_NAME = "id";

    public static final String AMOUNT_FIELD_NAME = "amount";

    public static final String FROM_CURRENCY_CODE_FIELD_NAME = "fromCurrencyISOCode";

    public static final String TO_CURRENCY_CODE_FIELD_NAME = "toCurrencyISOCode";

    private final DealDetailValidationService dealDetailValidationService;

    public DealDetailValidator(DealDetailValidationService dealDetailValidationService) {
        this.dealDetailValidationService = dealDetailValidationService;
    }

    @Override
    public boolean isValid(DealDetail dealDetail, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        boolean isValid = true;

        //checking if deal detail already exists
        if (!isDealDetailValid(dealDetail, context)) {
            isValid = false;
        }
        //checking if deal detail's amount valid
        if (!isAmountValid(dealDetail, context)) {
            isValid = false;
        }
        //checking if currency codes are not the same
        if (!areCurrencyCodesValid(dealDetail, context)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean areCurrencyCodesValid(DealDetail dealDetail, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (!dealDetailValidationService.areCurrencyCodesValid(dealDetail)) {
            ValidatorHelper.addFieldConstraintViolation(context, CommonMessagesEnum.VALIDATION_DUPLICATED_CURRENCY_CODES.getKey(),
                    FROM_CURRENCY_CODE_FIELD_NAME);
            ValidatorHelper.addFieldConstraintViolation(context, CommonMessagesEnum.VALIDATION_DUPLICATED_CURRENCY_CODES.getKey(),
                    TO_CURRENCY_CODE_FIELD_NAME);
            isValid = false;

        }
        return isValid;
    }

    private boolean isAmountValid(DealDetail dealDetail, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (dealDetail.getAmount() != null && dealDetail.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            ValidatorHelper.addFieldConstraintViolation(context, CommonMessagesEnum.VALIDATION_NEGATIVE_AMOUNT.getKey(),
                    AMOUNT_FIELD_NAME);
            isValid = false;
        }
        return isValid;
    }

    private boolean isDealDetailValid(DealDetail dealDetail, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (dealDetail.getId() != null && dealDetailValidationService.isDealDetailDuplicated(dealDetail.getId())) {
            ValidatorHelper.addFieldConstraintViolation(context, CommonMessagesEnum.VALIDATION_ALREADY_EXISTS.getKey(),
                    ID_FIELD_NAME);
            isValid = false;
        }
        return isValid;
    }
}
