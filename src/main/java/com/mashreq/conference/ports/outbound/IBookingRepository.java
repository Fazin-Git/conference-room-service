package com.mashreq.conference.ports.outbound;

import com.mashreq.conference.domain.model.BookingResponse;
import com.mashreq.conference.persistence.entity.ConferenceRoom;

import java.time.LocalDateTime;
import java.util.List;

public interface IBookingRepository {
    List<BookingResponse> findOverlappingBookings(Long roomId, LocalDateTime startTime, LocalDateTime endTime);
    boolean hasOverlappingBookings(Long roomId,LocalDateTime startTime,LocalDateTime endTime);
    List<Long> findBookedRoomIds(LocalDateTime startTime, LocalDateTime endTime);

}