package com.mashreq.conference.adapters.outbound.web;

import com.mashreq.conference.domain.model.BookingRequest;
import com.mashreq.conference.domain.model.ConferenceRoom;
import com.mashreq.conference.ports.inbound.BookingController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class OutBookingControllerAdapter implements BookingController {

    @Override
    public ResponseEntity<?> bookRoom(String roomId, BookingRequest bookingRequest) {
        return null;
    }

    @Override
    public ResponseEntity<List<ConferenceRoom>> getAvailableRooms(LocalTime startTime, LocalTime endTime) {
        return null;
    }
}