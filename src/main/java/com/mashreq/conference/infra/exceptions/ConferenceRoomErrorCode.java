package com.mashreq.conference.infra.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ConferenceRoomErrorCode  {

    //General error codes
    E_INTERNAL_SERVER_ERROR("E_INTERNAL_SERVER_ERROR", "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR),//500
    E_REQUEST_PARAMETER_MISSING("E_REQUEST_PARAMETER_MISSING", "Missing request parameter ", HttpStatus.NOT_ACCEPTABLE),
    E_INVALID_BOOKING_DATE("E_INVALID_BOOKING_DATE", "Booking can only be done for the current date", HttpStatus.BAD_REQUEST),
    E_BOOKING_INTERVAL_NOT_ALLOWED("E_BOOKING_INTERVAL_NOT_ALLOWED", "Booking should be in intervals of 15 minutes", HttpStatus.BAD_REQUEST),
    E_UNDER_MAINTENANCE_TIME("E_UNDER_MAINTENANCE_TIME", "Booking cannot be done during maintenance time", HttpStatus.BAD_REQUEST),
    E_BOOKING_OVERLAPPED("E_BOOKING_OVERLAPPED", "Booking overlaps with existing bookings fro the same room", HttpStatus.BAD_REQUEST),
    E_NO_OF_PARTICIPANTS_EXCEEDED("E_NO_OF_PARTICIPANTS_EXCEEDED", "Number of people should be greater than 1 and less than or equal to the maximum capacity of the room", HttpStatus.BAD_REQUEST),
    E_MEETING_ROOM_NOT_FOUND("E_MEETING_ROOM_NOT_FOUND", "Conference Room not found.", HttpStatus.INTERNAL_SERVER_ERROR),

    E_INVALID_ARGUMENTS("E_INVALID_ARGUMENTS", "Invalid method arguments ", HttpStatus.BAD_REQUEST);

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    ConferenceRoomErrorCode(String errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}