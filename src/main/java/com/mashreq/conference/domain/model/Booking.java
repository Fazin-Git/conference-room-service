package com.mashreq.conference.domain.model;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record Booking(Long id, Long roomId, Long userId, LocalTime startTime, LocalTime endTime) {
    // Additional constructor and methods can be added if needed
}