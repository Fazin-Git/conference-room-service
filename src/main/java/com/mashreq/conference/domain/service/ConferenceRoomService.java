package com.mashreq.conference.domain.service;

import com.mashreq.conference.domain.model.ConferenceRoom;

import java.util.List;

public interface ConferenceRoomService {
    List<ConferenceRoom> getAllRooms();

    ConferenceRoom getRoomById(Long roomId);

    // Other methods
}