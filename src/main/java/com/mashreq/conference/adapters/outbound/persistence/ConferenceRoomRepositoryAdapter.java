package com.mashreq.conference.adapters.outbound.persistence;

import com.mashreq.conference.domain.model.ConferenceRoomRes;
import com.mashreq.conference.persistence.entity.ConferenceRoom;
import com.mashreq.conference.persistence.repository.ConferenceRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ConferenceRoomRepositoryAdapter implements com.mashreq.conference.ports.outbound.ConferenceRoomRepository {

    private final ConferenceRoomRepository conferenceRoomRepository;

    public List<ConferenceRoomRes> findAll() {
        return conferenceRoomRepository.findAll().stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }
    private ConferenceRoomRes convertToDomain(ConferenceRoom conferenceRoom) {
        return ConferenceRoomRes.builder()
                .id(conferenceRoom.getId())
                .name(conferenceRoom.getName())
                .maxCapacity(conferenceRoom.getMaxCapacity()).build();
    }

}