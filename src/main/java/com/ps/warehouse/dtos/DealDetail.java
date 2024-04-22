package com.ps.warehouse.dtos;

import com.ps.warehouse.validation.annotations.ValidDealDetail;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
@ValidDealDetail
public class DealDetail {
    @NotNull
    private Long id;

    @NotNull
    private String fromCurrencyISOCode;

    @NotNull
    private String toCurrencyISOCode;

    @NotNull
    private Date dealTimeStamp;

    @NotNull
    private BigDecimal amount;
}
