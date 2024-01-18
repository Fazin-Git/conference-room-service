package com.mashreq.conference.servicel.impl;


import com.mashreq.conference.adapters.outbound.persistence.BookingRepositoryAdapter;
import com.mashreq.conference.domain.model.BookingRequest;
import com.mashreq.conference.domain.model.BookingResponse;
import com.mashreq.conference.domain.service.impl.BookingServiceImpl;
import com.mashreq.conference.infra.exceptions.ConferenceRoomException;
import com.mashreq.conference.persistence.entity.Booking;
import com.mashreq.conference.persistence.entity.ConferenceRoom;
import com.mashreq.conference.persistence.repository.ConferenceRoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    BookingRepositoryAdapter bookingRepositoryAdapter;

    @Mock
    ConferenceRoomRepository conferenceRoomRepository;

    @InjectMocks
    BookingServiceImpl bookingService;

    @DisplayName("Create booking test")
    @Test
    void createBookingTest() {
        BookingRequest bookingRequest = new BookingRequest(2L, LocalDateTime.now().plusHours(1).withMinute(15),LocalDateTime.now().plusHours(1).withMinute(30),7);
        ConferenceRoom room = new ConferenceRoom();
        room.setId(2l);
        room.setName("Beauty");
        room.setMaxCapacity(7);
        given(conferenceRoomRepository.findById(anyLong())).willReturn(Optional.of(room));
        given(bookingRepositoryAdapter.saveBooking(any(Booking.class))).willReturn(new BookingResponse(2L,LocalDateTime.now().plusHours(1).withMinute(15),LocalDateTime.now().plusHours(1).withMinute(30),7));
        BookingResponse booking = bookingService.createBooking(2L,bookingRequest);
        assertThat(booking).isNotNull();
    }

    @DisplayName("Create with exception overlapped booking")
    @Test
    void createBookingExceptionOverlappedBooking() {
        BookingRequest bookingRequest = new BookingRequest(2L, LocalDateTime.now().plusHours(1).withMinute(15),LocalDateTime.now().plusHours(1).withMinute(30),7);
        ConferenceRoom room = new ConferenceRoom();
        room.setId(2l);
        room.setName("Beauty");
        room.setMaxCapacity(7);
        given(conferenceRoomRepository.findById(anyLong())).willReturn(Optional.of(room));
        given(bookingRepositoryAdapter.hasOverlappingBookings(anyLong(),any(LocalDateTime.class),any(LocalDateTime.class))).willReturn(true);
        assertThrowsExactly(ConferenceRoomException.class, () -> bookingService.createBooking(2L,bookingRequest));

    }

    @DisplayName("Try booking with Unknown meeting id and throws exception ")
    @Test
    void createBookingWithUnknownMeetingId() {
        BookingRequest bookingRequest = new BookingRequest(10L, LocalDateTime.now().plusHours(1).withMinute(15),LocalDateTime.now().plusHours(1).withMinute(30),7);
        given(conferenceRoomRepository.findById(anyLong())).willReturn(Optional.empty());
        assertThrowsExactly(ConferenceRoomException.class, () -> bookingService.createBooking(2L,bookingRequest));

    }

    @DisplayName("Create with exception Non supported participants")
    @Test
    void createBookingExceptionUnSupportedParticipants() {
        BookingRequest bookingRequest = new BookingRequest(2L, LocalDateTime.now().plusHours(1).withMinute(15),LocalDateTime.now().plusHours(1).withMinute(30),25);
        ConferenceRoom room = new ConferenceRoom();
        room.setId(2l);
        room.setName("Beauty");
        room.setMaxCapacity(7);
        given(conferenceRoomRepository.findById(anyLong())).willReturn(Optional.of(room));
        assertThrowsExactly(ConferenceRoomException.class, () -> bookingService.createBooking(2L,bookingRequest));

    }


    @DisplayName("Book by number of participants ")
    @Test
    void bookRoomByNumberOfParticipants() {
        BookingRequest bookingRequest = BookingRequest.builder()
                .startTime(LocalDateTime.now().plusHours(2).withMinute(15))
                .endTime(LocalDateTime.now().plusHours(2).withMinute(30))
                .numOfPeople(7)
                .build();
        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setId(2L);
        conferenceRoom.setName("Beauty");
        conferenceRoom.setMaxCapacity(7);
        given(conferenceRoomRepository.findAllByMaxCapacityGreaterThanEqualOrderByMaxCapacityAsc(anyInt())).willReturn(List.of(conferenceRoom));
        given(bookingRepositoryAdapter.saveBooking(any(Booking.class))).willReturn(new BookingResponse(2L,LocalDateTime.now().plusHours(1).withMinute(15),LocalDateTime.now().plusHours(1).withMinute(30),7));
        BookingResponse bookingResponse = bookingService.bookRoomByNumberOfParticipants(bookingRequest);
        assertThat(bookingResponse).isNotNull();
    }
}
