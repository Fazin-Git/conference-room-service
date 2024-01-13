package com.mashreq.conference.domain.model;

import lombok.Builder;

@Builder
public record ConferenceRoomRes(Long id,String name, int maxCapacity) {
}