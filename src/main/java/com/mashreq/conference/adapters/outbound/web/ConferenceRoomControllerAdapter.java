package com.mashreq.conference.adapters.outbound.web;

import com.mashreq.conference.domain.model.ConferenceRoomReq;
import com.mashreq.conference.ports.inbound.ConferenceRoomController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
@RestController
@RequestMapping("/api/conference-rooms")
public class ConferenceRoomControllerAdapter implements ConferenceRoomController {
    @Override
    public ResponseEntity<List<ConferenceRoomReq>> getAvailableRooms() {
        return null;
    }

    @Override
    public ResponseEntity<ConferenceRoomReq> getRoomById(Long roomId) {
        return null;
    }
    // Inject ConferenceRoomService and delegate calls
}*/
