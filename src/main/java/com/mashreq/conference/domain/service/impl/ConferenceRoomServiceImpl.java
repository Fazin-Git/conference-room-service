package com.mashreq.conference.domain.service.impl;

import com.mashreq.conference.adapters.outbound.persistence.BookingRepositoryAdapter;
import com.mashreq.conference.adapters.outbound.persistence.ConferenceRoomRepositoryAdapter;
import com.mashreq.conference.domain.model.ConferenceRoomReq;
import com.mashreq.conference.domain.model.ConferenceRoomRes;
import com.mashreq.conference.domain.service.ConferenceRoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ConferenceRoomServiceImpl implements ConferenceRoomService {

    private final BookingRepositoryAdapter bookingRepositoryAdapter;

    private final ConferenceRoomRepositoryAdapter conferenceRoomRepositoryAdapter;
    @Override
    public List<ConferenceRoomRes> getAvailableRooms(LocalDateTime startTime, LocalDateTime endTime) {
        List<Long> bookedRoomIds = bookingRepositoryAdapter.findBookedRoomIds(startTime, endTime);
        if (bookedRoomIds.isEmpty()) {
            return conferenceRoomRepositoryAdapter.findAll();  // Return all rooms if none are booked
        } else {
            return conferenceRoomRepositoryAdapter.findAll().stream()
                    .filter(room -> !bookedRoomIds.contains(room.id()))
                    .toList();
        }
    }

    @Override
    public ConferenceRoomReq getRoomById(Long roomId) {
        return null;
    }
}
