package com.ps.warehouse.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.ps.warehouse.DealDetailStubs;
import com.ps.warehouse.dtos.DealDetail;
import com.ps.warehouse.utils.CommonMessagesEnum;
import com.ps.warehouse.validation.service.DealDetailValidationService;
import com.ps.warehouse.validation.validators.DealDetailValidator;
import java.math.BigDecimal;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintViolationCreationContext;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DealDetailValidatorTest {

    private static final String BASE_PATH = "";

    @Mock
    private DealDetailValidationService dealDetailValidationService;

    @InjectMocks
    private DealDetailValidator target;

    private static ConstraintValidatorContextImpl createConstraintValidatorContext() {
        return createConstraintValidatorContext(createConstraintValidatorContextBasePath());
    }

    private static ConstraintValidatorContextImpl createConstraintValidatorContext(PathImpl path) {
        return new ConstraintValidatorContextImpl(null, path, null, null, null, null);
    }

    private static PathImpl createConstraintValidatorContextBasePath() {
        PathImpl path = PathImpl.createPathFromString(BASE_PATH);
        path.addBeanNode();
        return path;
    }

    @Test
    void isValid_validEntry_success() {
        ConstraintValidatorContextImpl context = createConstraintValidatorContext();

        DealDetail dealDetail = DealDetailStubs.getDealDetail();
        when(dealDetailValidationService.isDealDetailDuplicated(dealDetail.getId()))
                .thenReturn(false);
        when(dealDetailValidationService.areCurrencyCodesValid(dealDetail))
                .thenReturn(true);

        boolean isValid = target.isValid(dealDetail, context);

        assertTrue(isValid);
    }

    @Test
    void isValid_invalidAmount_returnsViolation() {
        ConstraintValidatorContextImpl context = createConstraintValidatorContext();

        DealDetail dealDetail = DealDetailStubs.getDealDetail();
        dealDetail.setAmount(BigDecimal.valueOf(-1));
        when(dealDetailValidationService.isDealDetailDuplicated(dealDetail.getId()))
                .thenReturn(false);
        when(dealDetailValidationService.areCurrencyCodesValid(dealDetail))
                .thenReturn(true);

        boolean isValid = target.isValid(dealDetail, context);

        assertFalse(isValid);

        assertEquals(1, context.getConstraintViolationCreationContexts().size());

        ConstraintViolationCreationContext constraintViolationCreationContext = context.getConstraintViolationCreationContexts().getFirst();

        assertEquals(DealDetailValidator.AMOUNT_FIELD_NAME, constraintViolationCreationContext.getPath().toString());
        assertEquals(CommonMessagesEnum.VALIDATION_NEGATIVE_AMOUNT.getKey(), constraintViolationCreationContext.getMessage());
    }

    @Test
    void isValid_duplicatedEntry_returnsViolation() {
        ConstraintValidatorContextImpl context = createConstraintValidatorContext();

        DealDetail dealDetail = DealDetailStubs.getDealDetail();
        when(dealDetailValidationService.isDealDetailDuplicated(dealDetail.getId()))
                .thenReturn(true);
        when(dealDetailValidationService.areCurrencyCodesValid(dealDetail))
                .thenReturn(true);

        boolean isValid = target.isValid(dealDetail, context);

        assertFalse(isValid);

        assertEquals(1, context.getConstraintViolationCreationContexts().size());

        ConstraintViolationCreationContext constraintViolationCreationContext = context.getConstraintViolationCreationContexts().getFirst();

        assertEquals(DealDetailValidator.ID_FIELD_NAME, constraintViolationCreationContext.getPath().toString());
        assertEquals(CommonMessagesEnum.VALIDATION_ALREADY_EXISTS.getKey(), constraintViolationCreationContext.getMessage());
    }

    @Test
    void isValid_duplicatedCurrencyCodes_returnsViolation() {
        ConstraintValidatorContextImpl context = createConstraintValidatorContext();

        DealDetail dealDetail = DealDetailStubs.getDealDetail();
        when(dealDetailValidationService.isDealDetailDuplicated(dealDetail.getId()))
                .thenReturn(false);
        when(dealDetailValidationService.areCurrencyCodesValid(dealDetail))
                .thenReturn(false);

        boolean isValid = target.isValid(dealDetail, context);

        assertFalse(isValid);

        assertEquals(2, context.getConstraintViolationCreationContexts().size());

        ConstraintViolationCreationContext constraintViolationCreationContext1 = context.getConstraintViolationCreationContexts().getFirst();
        ConstraintViolationCreationContext constraintViolationCreationContext2 = context.getConstraintViolationCreationContexts().get(1);

        assertEquals(DealDetailValidator.FROM_CURRENCY_CODE_FIELD_NAME, constraintViolationCreationContext1.getPath().toString());
        assertEquals(CommonMessagesEnum.VALIDATION_DUPLICATED_CURRENCY_CODES.getKey(), constraintViolationCreationContext1.getMessage());

        assertEquals(DealDetailValidator.TO_CURRENCY_CODE_FIELD_NAME, constraintViolationCreationContext2.getPath().toString());
        assertEquals(CommonMessagesEnum.VALIDATION_DUPLICATED_CURRENCY_CODES.getKey(), constraintViolationCreationContext2.getMessage());
    }
}
