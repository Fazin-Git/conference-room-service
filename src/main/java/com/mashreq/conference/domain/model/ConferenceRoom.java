package com.mashreq.conference.domain.model;

import lombok.Builder;

@Builder
public record ConferenceRoom(Long id, String name, int maxCapacity) {
    // Additional constructor and methods can be added if needed
}