package com.mashreq.conference.persistence.repository;

import com.mashreq.conference.persistence.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {
    // Additional JPA repository methods if needed
}