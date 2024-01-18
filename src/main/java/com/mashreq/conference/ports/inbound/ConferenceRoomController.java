package com.mashreq.conference.ports.inbound;

import com.mashreq.conference.domain.model.ConferenceRoomReq;
import com.mashreq.conference.domain.model.ConferenceRoomRes;
import com.mashreq.conference.domain.model.Response;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface ConferenceRoomController {
    ResponseEntity<Response<List<ConferenceRoomRes>>> getAvailableRooms(LocalDateTime startTime, LocalDateTime endTime);

    ResponseEntity<Response<List<ConferenceRoomRes>>> getAllRooms();

    // Other methods
}