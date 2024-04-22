package com.ps.warehouse.services;

import com.ps.warehouse.dtos.DealDetail;
import com.ps.warehouse.entities.DealDetailEntity;
import com.ps.warehouse.mappers.DealDetailMapper;
import com.ps.warehouse.repositories.DealDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class DealDetailServiceImpl implements DealDetailService {

    private final DealDetailRepository dealDetailRepository;

    private final DealDetailMapper dealDetailMapper;

    public DealDetailServiceImpl(DealDetailRepository dealDetailRepository, DealDetailMapper dealDetailMapper) {
        this.dealDetailRepository = dealDetailRepository;
        this.dealDetailMapper = dealDetailMapper;
    }

    @Override
    public DealDetail add(DealDetailEntity dealDetailEntity) {
        return dealDetailMapper.entityToDto(dealDetailRepository.save(dealDetailEntity));
    }
}
