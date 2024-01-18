package com.mashreq.conference.domain.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record BookingRequest (
     Long roomId,
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
     LocalDateTime startTime,
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
     LocalDateTime endTime,
     int numOfPeople
){}