package com.mashreq.conference.persistence.repository;

import com.mashreq.conference.persistence.entity.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {
    List<ConferenceRoom> findAllByMaxCapacityGreaterThanEqualOrderByMaxCapacityAsc(int maxCapacity);

    @Query("SELECT DISTINCT cr FROM ConferenceRoom cr " +
            "WHERE NOT EXISTS (" +
            "   SELECT 1 FROM Booking b2 " +
            "   WHERE b2.conferenceRoom = cr " +
            "   AND CAST(:startTime AS DATE) = CAST(b2.startTime AS DATE) " +
            "   AND :startTime < b2.endTime AND :endTime > b2.startTime" +
            ")")
    List<ConferenceRoom> findAvailableRoomsForDay(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}