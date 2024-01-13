package com.mashreq.conference.adapters.outbound.web;

import com.mashreq.conference.domain.model.ConferenceRoom;
import com.mashreq.conference.ports.inbound.ConferenceRoomController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conference-rooms")
public class ConferenceRoomControllerAdapter implements ConferenceRoomController {
    @Override
    public ResponseEntity<List<ConferenceRoom>> getAllRooms() {
        return null;
    }

    @Override
    public ResponseEntity<ConferenceRoom> getRoomById(Long roomId) {
        return null;
    }
    // Inject ConferenceRoomService and delegate calls
}