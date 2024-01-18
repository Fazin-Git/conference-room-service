package com.mashreq.conference.adapters.outbound.persistence;

import com.mashreq.conference.domain.model.ConferenceRoomRes;
import com.mashreq.conference.persistence.entity.ConferenceRoom;
import com.mashreq.conference.persistence.repository.ConferenceRoomRepository;
import com.mashreq.conference.ports.outbound.IConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConferenceRoomRepositoryAdapter implements IConferenceRoomRepository {

    private final ConferenceRoomRepository conferenceRoomRepository;

    public List<ConferenceRoomRes> findAll() {
        return conferenceRoomRepository.findAll().stream()
                .map(this::convertToDomain)
                .toList();
    }
    private ConferenceRoomRes convertToDomain(ConferenceRoom conferenceRoom) {
        return ConferenceRoomRes.builder()
                .id(conferenceRoom.getId())
                .name(conferenceRoom.getName())
                .maxCapacity(conferenceRoom.getMaxCapacity()).build();
    }

    @Override
    public List<ConferenceRoomRes> findAvailableRoomsForDay(LocalDateTime startTime, LocalDateTime endTime) {
        return conferenceRoomRepository.findAvailableRoomsForDay(startTime,endTime)
                .stream().map(this::convertToDomain).toList();
    }
}