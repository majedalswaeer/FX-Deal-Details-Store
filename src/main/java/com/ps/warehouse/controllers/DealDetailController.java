package com.ps.warehouse.controllers;

import com.ps.warehouse.dtos.DealDetail;
import com.ps.warehouse.entities.DealDetailEntity;
import com.ps.warehouse.mappers.DealDetailMapper;
import com.ps.warehouse.rest_apis.DealDetailApi;
import com.ps.warehouse.services.DealDetailService;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
public class DealDetailController implements DealDetailApi {

    private final DealDetailMapper dealDetailMapper;

    private final DealDetailService dealDetailService;

    public DealDetailController(DealDetailMapper dealDetailMapper, DealDetailService dealDetailService) {
        this.dealDetailMapper = dealDetailMapper;
        this.dealDetailService = dealDetailService;
    }

    @Override
    public ResponseEntity<DealDetail> add(DealDetail dealDetail) {
        DealDetailEntity dealDetailEntity = dealDetailMapper.dtoTOEntity(dealDetail);
        DealDetail savedDealDetail = dealDetailService.add(dealDetailEntity);
        log.info("Deal Detail Created with id : {}", savedDealDetail.getId());
        return ResponseEntity.created(getLocation(savedDealDetail)).body(savedDealDetail);
    }

    private URI getLocation(DealDetail dealDetail) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                                          .path("/{id}")
                                          .buildAndExpand(dealDetail.getId())
                                          .toUri();
    }
}
