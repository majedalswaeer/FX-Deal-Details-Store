package com.ps.warehouse.dtos;

import com.ps.warehouse.validation.annotations.ValidDealDetail;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
@ValidDealDetail
public class DealDetail {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 3, message = "The ISO currency code must consist of exactly three characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency code format is invalid")
    private String fromCurrencyISOCode;

    @NotNull
    @Size(min = 3, max = 3, message = "The ISO currency code must consist of exactly three characters")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency code format is invalid")
    private String toCurrencyISOCode;

    @NotNull
    private Date dealTimeStamp;

    @NotNull
    private BigDecimal amount;
}
