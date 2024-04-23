package com.ps.warehouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.ps.warehouse.DealDetailStubs;
import com.ps.warehouse.entities.DealDetailEntity;
import com.ps.warehouse.mappers.DealDetailMapper;
import com.ps.warehouse.mappers.DealDetailMapperImpl;
import com.ps.warehouse.repositories.DealDetailRepository;
import com.ps.warehouse.services.DealDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
class DealDetailServiceImplTest {

    @Mock
    private DealDetailRepository dealDetailRepository;

    @Spy
    private DealDetailMapper dealDetailMapper = new DealDetailMapperImpl();

    @InjectMocks
    private DealDetailServiceImpl target;

    private final Answer<DealDetailEntity> saveAnswer = (args) -> {

        DealDetailEntity dealDetailEntity = args.getArgument(0);

        dealDetailEntity.setId(1L);

        return dealDetailEntity;
    };

    @Test
    void add_success() {
        DealDetailEntity dealDetailEntity = DealDetailStubs.getDealDetailEntity();
        when(dealDetailRepository.save(Mockito.any())).thenAnswer(saveAnswer);

        assertEquals(DealDetailStubs.getDealDetail(), target.add(dealDetailEntity));
    }

}
