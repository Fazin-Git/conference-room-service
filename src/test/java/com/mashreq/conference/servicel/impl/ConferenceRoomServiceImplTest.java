package com.mashreq.conference.servicel.impl;

import com.mashreq.conference.adapters.outbound.persistence.ConferenceRoomRepositoryAdapter;
import com.mashreq.conference.domain.model.ConferenceRoomRes;
import com.mashreq.conference.domain.service.impl.ConferenceRoomServiceImpl;
import com.mashreq.conference.infra.validator.BookingValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
 class ConferenceRoomServiceImplTest {


    @Mock
    ConferenceRoomRepositoryAdapter conferenceRoomRepositoryAdapter;
    @Mock
    BookingValidator bookingValidator;

    @InjectMocks
    ConferenceRoomServiceImpl conferenceRoomService;



    @DisplayName("Get available rooms")
    @Test
    void getAvailableRoomsTest() {
        given(conferenceRoomRepositoryAdapter.findAvailableRoomsForDay(any(LocalDateTime.class),any(LocalDateTime.class))).willReturn(List.of(new ConferenceRoomRes(2L,"Beauty",7)));
        List<ConferenceRoomRes> availableRooms = conferenceRoomService.getAvailableRooms(LocalDateTime.now().plusHours(1).withMinute(15), LocalDateTime.now().plusHours(1).withMinute(30));
        assertThat(availableRooms).isNotNull();
    }

    @DisplayName("Get all rooms")
    @Test
    void getAllRooms() {
        given(conferenceRoomRepositoryAdapter.findAll()).willReturn(List.of(new ConferenceRoomRes(2L,"Beauty",7)));
        List<ConferenceRoomRes> availableRooms = conferenceRoomService.getAllRooms();
        assertThat(availableRooms).isNotNull();
    }
}
