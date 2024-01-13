package com.mashreq.conference.domain.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConferenceRoomReq(LocalDateTime startTime, LocalDateTime endTime) {
}