package com.mashreq.conference.domain.service.impl;

import com.mashreq.conference.domain.model.ConferenceRoom;
import com.mashreq.conference.domain.service.ConferenceRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceRoomServiceImpl implements ConferenceRoomService {
    @Override
    public List<ConferenceRoom> getAllRooms() {
        return null;
    }

    @Override
    public ConferenceRoom getRoomById(Long roomId) {
        return null;
    }
}
