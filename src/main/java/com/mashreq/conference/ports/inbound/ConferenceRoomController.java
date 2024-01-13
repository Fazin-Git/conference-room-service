package com.mashreq.conference.ports.inbound;

import com.mashreq.conference.domain.model.ConferenceRoom;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConferenceRoomController {
    ResponseEntity<List<ConferenceRoom>> getAllRooms();

    ResponseEntity<ConferenceRoom> getRoomById(Long roomId);

    // Other methods
}