package com.mashreq.conference.domain.service;

import com.mashreq.conference.domain.model.BookingRequest;
import com.mashreq.conference.domain.model.BookingResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    boolean isBookingAllowed(Long roomId, LocalDateTime startTime, LocalDateTime endTime, int numberOfPeople);

    BookingResponse createBooking(Long roomId, BookingRequest bookingRequest);

    BookingResponse bookRoomByNumberOfParticipants(BookingRequest bookingRequest);

    List<BookingResponse> getAllBookings();

    // Other methods
}