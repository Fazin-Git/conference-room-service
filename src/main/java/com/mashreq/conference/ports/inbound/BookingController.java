package com.mashreq.conference.ports.inbound;

import com.mashreq.conference.domain.model.BookingDTO;
import com.mashreq.conference.domain.model.ConferenceRoom;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.List;

public interface BookingController {
    ResponseEntity<?> bookRoom(String roomId, BookingDTO bookingDTO) throws Exception;

    ResponseEntity<List<ConferenceRoom>> getAvailableRooms(LocalTime startTime, LocalTime endTime);

    // Other methods
}