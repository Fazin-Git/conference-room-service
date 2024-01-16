package com.mashreq.conference.domain.service.impl;

import com.mashreq.conference.adapters.outbound.persistence.BookingRepositoryAdapter;
import com.mashreq.conference.adapters.outbound.persistence.ConferenceRoomRepositoryAdapter;
import com.mashreq.conference.domain.model.BookingRequest;
import com.mashreq.conference.domain.model.ConferenceRoomReq;
import com.mashreq.conference.domain.model.ConferenceRoomRes;
import com.mashreq.conference.domain.service.ConferenceRoomService;
import com.mashreq.conference.infra.validator.BookingValidator;
import com.mashreq.conference.persistence.entity.ConferenceRoom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ConferenceRoomServiceImpl implements ConferenceRoomService {

    private final BookingRepositoryAdapter bookingRepositoryAdapter;

    private final ConferenceRoomRepositoryAdapter conferenceRoomRepositoryAdapter;

    private final BookingValidator bookingValidator;

    @Override
    public List<ConferenceRoomRes> getAvailableRooms(LocalDateTime startTime, LocalDateTime endTime) {
        validateRequest(startTime, endTime);
        return conferenceRoomRepositoryAdapter.findAvailableRoomsForDay(startTime, endTime);
    }

    private void validateRequest(LocalDateTime startTime, LocalDateTime endTime) {
        BookingRequest validRequest = new BookingRequest();
        validRequest.setStartTime(startTime);
        validRequest.setEndTime(endTime);
        Errors errors = new BeanPropertyBindingResult(validRequest, "validRequest");
        bookingValidator.validate(validRequest,errors);
    }

    @Override
    public ConferenceRoomReq getRoomById(Long roomId) {
        return null;
    }
}
