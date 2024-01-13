package com.mashreq.conference.ports.inbound;

import com.mashreq.conference.domain.model.BookingRequest;
import com.mashreq.conference.domain.model.ConferenceRoom;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.List;

public interface BookingController {
    ResponseEntity<?> bookRoom(String roomId, BookingRequest bookingRequest) throws Exception;

    ResponseEntity<List<ConferenceRoom>> getAvailableRooms(LocalTime startTime, LocalTime endTime);

    // Other methods
}