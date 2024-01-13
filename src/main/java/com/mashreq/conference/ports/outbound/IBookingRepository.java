package com.mashreq.conference.ports.outbound;

import com.mashreq.conference.persistence.entity.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface IBookingRepository {
    List<Booking> findOverlappingBookings(Long roomId, LocalDateTime startTime, LocalDateTime endTime);
    boolean hasOverlappingBookings(Long roomId,LocalDateTime startTime,LocalDateTime endTime);
    List<Long> findBookedRoomIds(LocalDateTime startTime, LocalDateTime endTime);
}