package com.mashreq.conference.ports.outbound;

import com.mashreq.conference.domain.model.BookingResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface IBookingRepository {
    List<BookingResponse> findAllBookingsForToday(LocalDateTime startOfDay,LocalDateTime endOfDay);
    boolean hasOverlappingBookings(Long roomId,LocalDateTime startTime,LocalDateTime endTime);

    List<BookingResponse> getAllBookingsOfRoom(LocalDateTime today, String roomId);
}