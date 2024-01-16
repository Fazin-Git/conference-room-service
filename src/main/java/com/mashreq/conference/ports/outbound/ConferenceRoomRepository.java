package com.mashreq.conference.ports.outbound;

import com.mashreq.conference.domain.model.ConferenceRoomRes;

import java.time.LocalDateTime;
import java.util.List;

public interface ConferenceRoomRepository {
    // ... (unchanged)
    List<ConferenceRoomRes> findAvailableRoomsForDay(LocalDateTime startTime, LocalDateTime endTime);
}