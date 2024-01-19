package com.mashreq.conference.adapters.outbound.persistence;

import com.mashreq.conference.domain.model.BookingResponse;
import com.mashreq.conference.persistence.entity.Booking;
import com.mashreq.conference.persistence.repository.BookingRepository;
import com.mashreq.conference.ports.outbound.IBookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookingRepositoryAdapter implements IBookingRepository {
    private final BookingRepository bookingRepository;

    public List<BookingResponse> findAllBookingsForToday(LocalDateTime startOfDay,LocalDateTime nextDayDateTime){
         return bookingRepository.findAllBookingsForToday(startOfDay,nextDayDateTime).stream().map(this::toDomainBooking).toList();
    }

    public boolean hasOverlappingBookings(Long roomId, LocalDateTime startTime, LocalDateTime endTime){
        return bookingRepository.hasOverlappingBookings(roomId,startTime,endTime);
    }

    @Override
    public List<BookingResponse> getAllBookingsOfRoom(LocalDateTime today, String roomId) {
        return bookingRepository.findByRoomId(Long.valueOf(roomId)).stream().map(this::toDomainBooking).toList();
    }

    public BookingResponse saveBooking(Booking booking){
        return Optional.of(bookingRepository.save(booking)).map(this::toDomainBooking).orElse(null);
    }
    private BookingResponse toDomainBooking(Booking booking){
        return new BookingResponse(booking.getConferenceRoom().getId(),booking.getStartTime(),booking.getEndTime(),booking.getNumOfPeople());
    }
}