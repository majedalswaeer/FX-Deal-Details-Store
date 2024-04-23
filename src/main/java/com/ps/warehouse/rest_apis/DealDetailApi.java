package com.ps.warehouse.rest_apis;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.ps.warehouse.dtos.DealDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Deal Details")
public interface DealDetailApi {

    String BASE_URL = "/deal-details";

    @Operation(summary = "Creates a new Deal Detail")
    @PostMapping(value = BASE_URL, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    ResponseEntity<DealDetail> add(@Valid @RequestBody DealDetail dealDetail);
}
