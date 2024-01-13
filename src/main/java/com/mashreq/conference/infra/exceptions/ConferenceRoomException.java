package com.mashreq.conference.infra.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@Data
@EqualsAndHashCode(callSuper = true)
public class ConferenceRoomException extends RuntimeException {
    private final String errorCode;
    private final Object errorDetails;
    private final HttpStatus httpStatus;


    public ConferenceRoomException(ConferenceRoomErrorCode roomErrorCode) {
        super(roomErrorCode.getErrorMessage());
        this.errorCode = roomErrorCode.getErrorCode();
        this.httpStatus = roomErrorCode.getHttpStatus();
        this.errorDetails = null;
    }

    public ConferenceRoomException(String errorMsg, String errorCode, Object errorDetails, HttpStatus httpStatus) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.httpStatus = httpStatus;
    }

}