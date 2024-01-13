package com.mashreq.conference.domain.service.impl;

import com.mashreq.conference.domain.model.BookingRequest;
import com.mashreq.conference.domain.service.BookingService;
import com.mashreq.conference.infra.exceptions.ConferenceRoomException;
import com.mashreq.conference.infra.exceptions.ConferenceRoomErrorCode;
import com.mashreq.conference.persistence.entity.Booking;
import com.mashreq.conference.persistence.entity.ConferenceRoom;
import com.mashreq.conference.persistence.repository.BookingRepository;
import com.mashreq.conference.persistence.repository.ConferenceRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final ConferenceRoomRepository conferenceRoomRepository;


    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, ConferenceRoomRepository conferenceRoomRepository) {
        this.bookingRepository = bookingRepository;
        this.conferenceRoomRepository = conferenceRoomRepository;
    }

    @Override
    public boolean isBookingAllowed(Long roomId, LocalDateTime startTime, LocalDateTime endTime, int numberOfPeople) {
        return bookingRepository.hasOverlappingBookings(roomId,startTime,endTime);
    }

    @Override
    public Booking createBooking(Long roomId, BookingRequest bookingRequest) {
        ConferenceRoom room = conferenceRoomRepository.findById(roomId).orElseThrow(() -> new ConferenceRoomException(ConferenceRoomErrorCode.E_MEETING_ROOM_NOT_FOUND));
        validateBookingRequest(bookingRequest.getNumOfPeople(), room);
        boolean isBookingOverlapped = bookingRepository.hasOverlappingBookings(roomId, bookingRequest.getStartTime(), bookingRequest.getEndTime());
        log.info("Overlap booking status : {} for room id {}",isBookingOverlapped,roomId);
        if (isBookingOverlapped) {
            throw new ConferenceRoomException(ConferenceRoomErrorCode.E_BOOKING_OVERLAPPED);
        }
        Booking booking = new Booking();
        booking.setConferenceRoom(room);
        booking.setStartTime(bookingRequest.getStartTime());
        booking.setEndTime(bookingRequest.getEndTime());
        booking.setNumOfPeople(bookingRequest.getNumOfPeople());
        return bookingRepository.save(booking);
    }
    private void validateBookingRequest(int numOfPeople, ConferenceRoom room) {
        if (numOfPeople <= 1 || numOfPeople > room.getMaxCapacity()) {
            throw new ConferenceRoomException(ConferenceRoomErrorCode.E_NO_OF_PARTICIPANTS_EXCEEDED);
        }
    }

}
