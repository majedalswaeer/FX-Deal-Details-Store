package com.ps.warehouse.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.ps.warehouse.DealDetailStubs;
import com.ps.warehouse.dtos.DealDetail;
import com.ps.warehouse.repositories.DealDetailRepository;
import com.ps.warehouse.validation.service.DealDetailValidationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DealDetailValidationServiceImplTest {

    @Mock
    private DealDetailRepository dealDetailRepository;

    @InjectMocks
    private DealDetailValidationServiceImpl target;

    @Test
    void isDealDetailDuplicated_returnsTrue() {
        DealDetail dealDetail = DealDetailStubs.getDealDetail();
        when(dealDetailRepository.existsById(dealDetail.getId().toString())).thenReturn(true);

        assertTrue(target.isDealDetailDuplicated(dealDetail.getId()));
    }

    @Test
    void isDealDetailDuplicated_returnsFalse() {
        DealDetail dealDetail = DealDetailStubs.getDealDetail();
        when(dealDetailRepository.existsById(dealDetail.getId().toString())).thenReturn(false);

        assertFalse(target.isDealDetailDuplicated(dealDetail.getId()));
    }

    @Test
    void areCurrencyCodesValid_returnsTrue() {
        DealDetail dealDetail = DealDetailStubs.getDealDetail();

        assertTrue(target.areCurrencyCodesValid(dealDetail));
    }

    @Test
    void areCurrencyCodesValid_returnsFalse() {
        DealDetail dealDetail = DealDetailStubs.getDealDetail();
        dealDetail.setToCurrencyISOCode("USD");
        assertFalse(target.areCurrencyCodesValid(dealDetail));
    }
}
