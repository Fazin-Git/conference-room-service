package com.mashreq.conference.domain.service;

import com.mashreq.conference.domain.model.ConferenceRoomRes;

import java.time.LocalDateTime;
import java.util.List;

public interface ConferenceRoomService {
    List<ConferenceRoomRes> getAvailableRooms(LocalDateTime startTime, LocalDateTime endTime);
    List<ConferenceRoomRes> getAllRooms();

    // Other methods
}