package com.mashreq.conference.domain.service;

import com.mashreq.conference.domain.model.BookingRequest;
import com.mashreq.conference.persistence.entity.Booking;

import java.time.LocalDateTime;

public interface BookingService {
    boolean isBookingAllowed(Long roomId, LocalDateTime startTime, LocalDateTime endTime, int numberOfPeople);

    Booking createBooking(Long roomId, BookingRequest bookingRequest);

    // Other methods
}