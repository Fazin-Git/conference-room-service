package com.mashreq.conference.adapters.outbound.persistence;

import com.mashreq.conference.persistence.entity.Booking;
import com.mashreq.conference.persistence.repository.BookingRepository;
import com.mashreq.conference.ports.outbound.IBookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingRepositoryAdapter implements IBookingRepository {
    // Inject JPA repository and implement methods
    private final BookingRepository bookingRepository;

    public List<Booking> findOverlappingBookings(Long roomId,LocalDateTime startTime,LocalDateTime endTime){
        return bookingRepository.findOverlappingBookings(roomId,startTime,endTime);
    }

    public boolean hasOverlappingBookings(Long roomId, LocalDateTime startTime, LocalDateTime endTime){
        return bookingRepository.hasOverlappingBookings(roomId,startTime,endTime);
    }

    @Override
    public List<Long> findBookedRoomIds(LocalDateTime startTime, LocalDateTime endTime) {
        return bookingRepository.findBookedRoomIds(startTime,endTime);
    }

    public Booking save(Booking booking){
        return bookingRepository.save(booking);
    }
}