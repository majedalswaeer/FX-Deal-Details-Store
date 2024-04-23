package com.ps.warehouse.validation.service;


import com.ps.warehouse.dtos.DealDetail;

public interface DealDetailValidationService {

    boolean isDealDetailDuplicated(Long id);

    boolean areCurrencyCodesValid(DealDetail dealDetail);
}
