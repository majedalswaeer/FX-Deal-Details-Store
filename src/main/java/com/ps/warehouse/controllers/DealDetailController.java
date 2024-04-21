package com.ps.warehouse.controllers;

import com.ps.warehouse.entities.DealDetailEntity;
import com.ps.warehouse.repositories.DealDetailRepository;
import com.ps.warehouse.rest_apis.DealDetailApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DealDetailController implements DealDetailApi {

    private final DealDetailRepository dealDetailRepository;

    public DealDetailController(DealDetailRepository dealDetailRepository) {
        this.dealDetailRepository = dealDetailRepository;
    }

    @Override
    public ResponseEntity<DealDetailEntity> add(DealDetailEntity dealDetail) {
        return ResponseEntity.ok(dealDetailRepository.save(dealDetail));
    }
}
