package com.mashreq.conference.domain.service.impl;

import com.mashreq.conference.adapters.outbound.persistence.BookingRepositoryAdapter;
import com.mashreq.conference.domain.model.BookingRequest;
import com.mashreq.conference.domain.model.BookingResponse;
import com.mashreq.conference.domain.service.BookingService;
import com.mashreq.conference.infra.exceptions.ConferenceRoomException;
import com.mashreq.conference.infra.exceptions.ConferenceRoomErrorCode;
import com.mashreq.conference.persistence.entity.Booking;
import com.mashreq.conference.persistence.entity.ConferenceRoom;
import com.mashreq.conference.persistence.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {
    private final BookingRepositoryAdapter bookingRepositoryAdapter;
    private final ConferenceRoomRepository conferenceRoomRepository;

    @Override
    public boolean isBookingAllowed(Long roomId, LocalDateTime startTime, LocalDateTime endTime, int numberOfPeople) {
        return bookingRepositoryAdapter.hasOverlappingBookings(roomId,startTime,endTime);
    }

    @Override
    public BookingResponse createBooking(Long roomId, BookingRequest bookingRequest) {
        ConferenceRoom room = conferenceRoomRepository.findById(roomId).orElseThrow(() -> new ConferenceRoomException(ConferenceRoomErrorCode.E_MEETING_ROOM_NOT_FOUND));
        validateBookingRequest(bookingRequest.numOfPeople(), room);
        checkForOverlappedBooking(roomId, bookingRequest);
        return bookingRepositoryAdapter.saveBooking(setBookingEntity(bookingRequest, room));
    }

    private void checkForOverlappedBooking(Long roomId, BookingRequest bookingRequest) {
        boolean isBookingOverlapped = bookingRepositoryAdapter.hasOverlappingBookings(roomId, bookingRequest.startTime(), bookingRequest.endTime());
        log.info("Overlap booking status : {} for room id {}",isBookingOverlapped, roomId);
        if (isBookingOverlapped) {
            throw new ConferenceRoomException(ConferenceRoomErrorCode.E_BOOKING_OVERLAPPED);
        }
    }

    @Override
    public BookingResponse bookRoomByNumberOfParticipants(BookingRequest bookingRequest) {
        ConferenceRoom room = conferenceRoomRepository.findAllByMaxCapacityGreaterThanEqualOrderByMaxCapacityAsc(bookingRequest.numOfPeople())
                .stream().findFirst().orElseThrow(() -> new ConferenceRoomException(ConferenceRoomErrorCode.E_MEETING_ROOM_NOT_FOUND));
        validateBookingRequest(bookingRequest.numOfPeople(), room);
        checkForOverlappedBooking(room.getId(), bookingRequest);
        return bookingRepositoryAdapter.saveBooking(setBookingEntity(bookingRequest, room));
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime nextDayDateTime = currentDateTime.plusDays(1);
        return bookingRepositoryAdapter.findAllBookingsForToday(currentDateTime,nextDayDateTime);
    }

    private Booking setBookingEntity(BookingRequest bookingRequest, ConferenceRoom room) {
        Booking booking = new Booking();
        booking.setConferenceRoom(room);
        booking.setStartTime(bookingRequest.startTime());
        booking.setEndTime(bookingRequest.endTime());
        booking.setNumOfPeople(bookingRequest.numOfPeople());
        return booking;
    }

    private void validateBookingRequest(int numOfPeople, ConferenceRoom room) {
        if (numOfPeople <= 1 || numOfPeople > room.getMaxCapacity()) {
            throw new ConferenceRoomException(ConferenceRoomErrorCode.E_NO_OF_PARTICIPANTS_EXCEEDED);
        }
    }

}
