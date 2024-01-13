package com.mashreq.conference.persistence.repository;

import com.mashreq.conference.persistence.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query("SELECT b FROM Booking b " +
            "WHERE b.conferenceRoom.id = :roomId " +
            "AND ((b.startTime BETWEEN :startTime AND :endTime) OR (b.endTime BETWEEN :startTime AND :endTime))")
    List<Booking> findOverlappingBookings(@Param("roomId") Long roomId,
                                          @Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    /*@Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END " +
            "FROM Booking b " +
            "WHERE b.conferenceRoom.id = :roomId " +
            "AND b.startTime >= :startTime " +
            "AND b.endTime <= :endTime " +
            "AND CAST(b.startTime AS DATE) = CAST(:startTime AS DATE)")
    boolean hasOverlappingBookings(@Param("roomId") Long roomId,
                                   @Param("startTime") LocalDateTime startTime,
                                   @Param("endTime") LocalDateTime endTime);*/

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Booking b " +
            "WHERE b.conferenceRoom.id = :roomId " +
            "AND b.endTime > :startTime " +
            "AND b.startTime < :endTime")
    boolean hasOverlappingBookings(@Param("roomId") Long roomId,
                                   @Param("startTime") LocalDateTime startTime,
                                   @Param("endTime") LocalDateTime endTime);
}
