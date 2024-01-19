package com.mashreq.conference.infra.exceptions;

import com.mashreq.conference.domain.model.Response;
import com.mashreq.conference.domain.model.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ConferenceRoomException.class)
    ResponseEntity<BookingErrorResponse> globalException(ConferenceRoomException exception) {
        BookingErrorResponse bookingErrorResponse = new BookingErrorResponse();
        bookingErrorResponse.setData(new ErrorData(exception.getErrorCode(), exception.getMessage(), exception.getErrorDetails()));
        bookingErrorResponse.setStatus(ResponseStatus.ERROR);
        bookingErrorResponse.setHasError(true);
        return new ResponseEntity<>(bookingErrorResponse, exception.getHttpStatus());
    }
    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Response response;
            ValidationErrorDto error = new ValidationErrorDto();
            for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
                error.getViolations().add(new ViolationDto(fieldError.getField(), fieldError.getDefaultMessage()));
            }
            response = Response.<ValidationErrorDto>builder()
                    .data(error)
                    .errorCode("E_INVALID_ARGUMENTS")
                    .status(ResponseStatus.ERROR)
                    .build();
            return ResponseEntity.status(status)
                    .body(response);
    }
}