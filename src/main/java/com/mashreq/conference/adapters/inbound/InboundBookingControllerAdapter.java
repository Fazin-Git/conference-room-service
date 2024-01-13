package com.mashreq.conference.adapters.inbound;

import com.mashreq.conference.domain.model.BookingDTO;
import com.mashreq.conference.domain.model.ConferenceRoom;
import com.mashreq.conference.domain.model.Response;
import com.mashreq.conference.domain.model.ResponseStatus;
import com.mashreq.conference.domain.service.BookingService;
import com.mashreq.conference.persistence.entity.Booking;
import com.mashreq.conference.ports.inbound.BookingController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/conference-rooms")
@RequiredArgsConstructor
@Slf4j
public class InboundBookingControllerAdapter implements BookingController {

    private final BookingService bookingService;

    @Override
    @Operation(summary = "Book room")
    @ApiResponse(responseCode = "200", description = "Room booked successfully.", content = @Content(mediaType = "application/json"))
    @PostMapping("/{roomId}/book-room")
    public ResponseEntity<Response<Booking>> bookRoom(@PathVariable String roomId, @RequestBody BookingDTO bookingDTO) throws Exception {
        ;
        return ResponseEntity.ok()
                .body(Response.<Booking>builder()
                        .data(bookingService.createBooking(Long.valueOf(roomId),bookingDTO))
                        .message("Room booked successfully.")
                        .status(ResponseStatus.SUCCESS)
                        .build());
    }


    @Operation(summary = "Book room")
    @ApiResponse(responseCode = "200", description = "Room booked successfully.", content = @Content(mediaType = "application/json"))
    @GetMapping("/test")
    public ResponseEntity<String> get() {
        return  new ResponseEntity<>(
                "SUCCESS", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ConferenceRoom>> getAvailableRooms(LocalTime startTime, LocalTime endTime) {
        return null;
    }
    // Inject BookingService and delegate calls
}