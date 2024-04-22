package com.ps.warehouse.rest_apis;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.ps.warehouse.dtos.DealDetail;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface DealDetailApi {

    String BASE_URL = "/deal-details";

    @PostMapping(value = BASE_URL, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<DealDetail> add(@Valid @RequestBody DealDetail dealDetail);
}
