package com.mashreq.conference.ports.inbound;

import com.mashreq.conference.domain.model.BookingRequest;
import com.mashreq.conference.domain.model.BookingResponse;
import com.mashreq.conference.domain.model.ConferenceRoomReq;
import com.mashreq.conference.domain.model.Response;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.List;

public interface BookingController {
    ResponseEntity<Response<BookingResponse>> bookRoomById(String roomId, BookingRequest bookingRequest);

    ResponseEntity<Response<List<BookingResponse>>> getAllBookings();

    ResponseEntity<Response<BookingResponse>> bookRoomByNumberOfParticipants(BookingRequest bookingRequest);

}