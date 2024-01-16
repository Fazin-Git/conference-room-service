package com.mashreq.conference.domain.service;

import com.mashreq.conference.domain.model.BookingRequest;
import com.mashreq.conference.domain.model.BookingResponse;

import java.time.LocalDateTime;

public interface BookingService {
    boolean isBookingAllowed(Long roomId, LocalDateTime startTime, LocalDateTime endTime, int numberOfPeople);

    BookingResponse createBooking(Long roomId, BookingRequest bookingRequest);

    BookingResponse bookRoomByNumberOfParticipants(BookingRequest bookingRequest);

    // Other methods
}