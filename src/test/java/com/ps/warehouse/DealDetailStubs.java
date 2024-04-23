package com.ps.warehouse;

import com.ps.warehouse.dtos.DealDetail;
import com.ps.warehouse.entities.DealDetailEntity;
import java.math.BigDecimal;
import java.util.Date;

public class DealDetailStubs {


    static final long ID = 1L;

    static final String TO_CURRENCY_ISO_CODE = "JOD";

    static final String FROM_CURRENCY_ISO_CODE = "USD";

    static final Date CONSISTENT_DATE = new Date(1640995200000L);

    static final BigDecimal AMOUNT = BigDecimal.TEN;

    public static DealDetailEntity getDealDetailEntity() {
        DealDetailEntity dealDetailEntity = new DealDetailEntity();
        dealDetailEntity.setId(ID);
        dealDetailEntity.setFromCurrencyISOCode(FROM_CURRENCY_ISO_CODE);
        dealDetailEntity.setToCurrencyISOCode(TO_CURRENCY_ISO_CODE);
        dealDetailEntity.setDealTimeStamp(CONSISTENT_DATE);
        dealDetailEntity.setAmount(AMOUNT);
        return dealDetailEntity;

    }

    public static DealDetail getDealDetail() {
        DealDetail dealDetail = new DealDetail();
        dealDetail.setId(ID);
        dealDetail.setFromCurrencyISOCode(FROM_CURRENCY_ISO_CODE);
        dealDetail.setToCurrencyISOCode(TO_CURRENCY_ISO_CODE);
        dealDetail.setDealTimeStamp(CONSISTENT_DATE);
        dealDetail.setAmount(AMOUNT);
        return dealDetail;

    }
}
