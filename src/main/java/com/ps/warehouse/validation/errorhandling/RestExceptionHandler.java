package com.ps.warehouse.validation.errorhandling;

import com.ps.warehouse.utils.CommonMessagesEnum;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Component
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public RestExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Handles {@link ConstraintViolationException}. Thrown when @Validated fails.
     *
     * @param ex      The ConstraintViolationException that is thrown
     * @param locale  Locale
     * @param request WebRequest
     * @return BadRequestMessage
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<BaseMessage> handleConstraintViolationException(ConstraintViolationException ex, Locale locale, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Retrieve all validation errors in a Violation list
        List<Violation> violations = ex.getConstraintViolations()
                                       .stream()
                                       .map(error -> Violation.builder()
                                                              .fieldName(error.getPropertyPath().toString())
                                                              .rejectedValue(error.getInvalidValue())
                                                              .message(error.getMessage())
                                                              .build())
                                       .toList();

        // Create the error message
        String message = messageSource.getMessage(CommonMessagesEnum.VALIDATION_FAIL.getKey(), null, locale);

        BaseMessage errorMessage = BaseMessage.builder()
                                              .message(message)
                                              .status(status.value())
                                              .path(getPath(request))
                                              .violations(violations)
                                              .build();

        return ResponseEntity.badRequest()
                             .body(errorMessage);
    }

    /**
     * Handle {@link MethodArgumentNotValidException}. Triggered when an object fails @Valid validation.
     *
     * @param ex      The MethodArgumentNotValidException that is thrown
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return BadRequestMessage
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
            WebRequest request) {
        // Retrieve all validation errors in a Violation list
        List<Violation> violations = ex.getBindingResult().getFieldErrors()
                                       .stream()
                                       .map(error -> {
                                           error.getField();
                                           return Violation.builder()
                                                           .fieldName(error.getField())
                                                           .rejectedValue(error.getRejectedValue())
                                                           .message(constructMessage(error))
                                                           .build();
                                       })
                                       .toList();

        // Create the error message
        String message = messageSource.getMessage(CommonMessagesEnum.VALIDATION_FAIL.getKey(), null, LocaleContextHolder.getLocale());

        BaseMessage errorMessage = BaseMessage.builder()
                                              .message(message)
                                              .status(status.value())
                                              .path(getPath(request))
                                              .violations(violations)
                                              .build();

        return ResponseEntity.badRequest()
                             .body(errorMessage);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status,
            WebRequest request) {

        // Create the error message
        String message = messageSource.getMessage(CommonMessagesEnum.SOMETHING_WENT_WRONG.getKey(), null, LocaleContextHolder.getLocale());

        BaseMessage errorMessage = BaseMessage.builder()
                                              .message(message)
                                              .status(status.value())
                                              .path(getPath(request))
                                              .build();

        return ResponseEntity.badRequest()
                             .body(errorMessage);
    }

    /**
     * Handler that serves as a catch-all. If no specific error handler is found for an exception, this method will be invoked.
     *
     * @param ex      Exception that is thrown
     * @param locale  Locale
     * @param request WebRequest
     * @return GenericErrorMessage
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<BaseMessage> handleAllExceptions(Exception ex, Locale locale, WebRequest request) {

        log.error("Unhandled exception: ", ex);

        String message = messageSource.getMessage(CommonMessagesEnum.VALIDATION_FAIL.getKey(), null, locale);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        BaseMessage genericErrorMessage = BaseMessage.builder()
                                                     .message(message)
                                                     .status(status.value())
                                                     .path(getPath(request))
                                                     .detailMessage(ex.getLocalizedMessage())
                                                     .build();

        return ResponseEntity.status(status)
                             .body(genericErrorMessage);
    }

    private String getPath(WebRequest request) {
        return request.getDescription(false).substring(4);
    }

    private String constructMessage(FieldError error) {
        try {
            return messageSource.getMessage(Objects.requireNonNull(error.getDefaultMessage()), null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return error.getDefaultMessage();
        }
    }
}
