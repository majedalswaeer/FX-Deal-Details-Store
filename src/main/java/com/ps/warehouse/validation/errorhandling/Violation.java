package com.ps.warehouse.validation.errorhandling;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for validation violations, includes the field name that was violated, the value that has as well as an indicating error message.
 */
@Data
@Builder
public class Violation {

    @Schema(description = "The property path of the field that caused validation to fail")
    private final String fieldName;

    @Schema(description = "The actual value that caused the violation")
    private final Object rejectedValue;

    @Schema(description = "A descriptive message of the violation")
    private final String message;
}