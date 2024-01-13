package com.mashreq.conference.ports.inbound;

import com.mashreq.conference.domain.model.ConferenceRoomReq;
import com.mashreq.conference.domain.model.ConferenceRoomRes;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ConferenceRoomController {
    ResponseEntity<List<ConferenceRoomRes>> getAvailableRooms(LocalDateTime startTime, LocalDateTime endTime);

    ResponseEntity<ConferenceRoomReq> getRoomById(Long roomId);

    // Other methods
}