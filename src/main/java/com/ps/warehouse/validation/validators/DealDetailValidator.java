package com.ps.warehouse.validation.validators;

import com.ps.warehouse.dtos.DealDetail;
import com.ps.warehouse.repositories.DealDetailRepository;
import com.ps.warehouse.utils.CommonMessagesEnum;
import com.ps.warehouse.validation.ValidatorHelper;
import com.ps.warehouse.validation.annotations.ValidDealDetail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DealDetailValidator implements ConstraintValidator<ValidDealDetail, DealDetail> {

    static final String ID_FIELD_NAME = "id";

    private final DealDetailRepository dealDetailRepository;

    public DealDetailValidator(DealDetailRepository dealDetailRepository) {
        this.dealDetailRepository = dealDetailRepository;
    }

    @Override
    public boolean isValid(DealDetail dealDetail, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        boolean isValid = true;
        if (isDealDetailExists(dealDetail, context)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean isDealDetailExists(DealDetail dealDetail, ConstraintValidatorContext context) {
        boolean isExists = false;
        if (dealDetail.getId() != null && dealDetailRepository.existsById(dealDetail.getId().toString())) {
            ValidatorHelper.addFieldConstraintViolation(context, CommonMessagesEnum.VALIDATION_ALREADY_EXISTS.getKey(),
                    ID_FIELD_NAME);
            isExists = true;
        }
        return isExists;
    }
}