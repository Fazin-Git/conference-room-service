package com.mashreq.conference.domain.model;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record MaintenanceTiming(Long id, LocalTime startTime, LocalTime endTime) {
    // Additional constructor and methods can be added if needed
}