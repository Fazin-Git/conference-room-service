package com.mashreq.conference.infra.exceptions;

import com.mashreq.conference.domain.model.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ConferenceRoomException.class)
    ResponseEntity<BookingErrorResponse> smeGlobalException(ConferenceRoomException exception) {
        BookingErrorResponse bookingErrorResponse = new BookingErrorResponse();
        bookingErrorResponse.setData(new ErrorData(exception.getErrorCode(), exception.getMessage(), exception.getErrorDetails()));
        bookingErrorResponse.setStatus(ResponseStatus.ERROR);
        bookingErrorResponse.setHasError(true);
        return new ResponseEntity<>(bookingErrorResponse, exception.getHttpStatus());
    }
}