package com.ps.warehouse.validation.service;

import com.ps.warehouse.dtos.DealDetail;
import com.ps.warehouse.repositories.DealDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class DealDetailValidationServiceImpl implements DealDetailValidationService {

    private final DealDetailRepository dealDetailRepository;

    public DealDetailValidationServiceImpl(DealDetailRepository dealDetailRepository) {
        this.dealDetailRepository = dealDetailRepository;
    }

    @Override
    public boolean isDealDetailDuplicated(Long id) {
        return dealDetailRepository.existsById(id.toString());
    }

    @Override
    public boolean areCurrencyCodesValid(DealDetail dealDetail) {
        return !(dealDetail.getToCurrencyISOCode() != null && dealDetail.getFromCurrencyISOCode() != null && dealDetail.getFromCurrencyISOCode()
                                                                                                                       .equals(dealDetail.getToCurrencyISOCode()));
    }
}
