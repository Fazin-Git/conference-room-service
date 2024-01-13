package com.mashreq.conference.adapters.outbound.persistence;

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


    // Inject JPA repository and implement methods
    public List<com.mashreq.conference.domain.model.ConferenceRoom> findAll() {
        return conferenceRoomRepository.findAll().stream()
                .map(this::convertToDomain)
                .collect(Collectors.toList());
    }
    private com.mashreq.conference.domain.model.ConferenceRoom convertToDomain(ConferenceRoom conferenceRoom) {
        // Convert JPA entity to domain model
        return com.mashreq.conference.domain.model.ConferenceRoom.builder()
                .id(conferenceRoom.getId())
                .name(conferenceRoom.getName())
                .maxCapacity(conferenceRoom.getMaxCapacity()).build();
        // Map other fields if needed
    }

}