package com.ps.warehouse.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "deal_detail")
public class DealDetailEntity {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "FROM_CURRENCY_ISO_Code")
    private String  fromCurrencyISOCode;

    @Column(name = "TO_CURRENCY_ISO_Code")
    private String toCurrencyISOCode;

    @Column(name = "TIME_STAMP")
    private Date dealTimeStamp;

    @Column(name = "AMOUNT")
    private BigDecimal amount;
}
