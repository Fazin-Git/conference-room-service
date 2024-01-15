package com.mashreq.conference.domain.model;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookingResponse (Long roomId,LocalDateTime startTime,LocalDateTime endTime,int numOfPeople ){
}

