package com.ps.warehouse.validation.errorhandling;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseMessage {

    private final String message;

    private final int status;

    private final LocalDateTime timestamp = LocalDateTime.now();

    private final String detailMessage;

    private final List<Violation> violations;

    private final String path;
}