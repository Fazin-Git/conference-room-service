package com.mashreq.conference.adapters.inbound;

import com.mashreq.conference.domain.model.*;
import com.mashreq.conference.domain.model.ResponseStatus;
import com.mashreq.conference.domain.service.BookingService;
import com.mashreq.conference.infra.validator.BookingValidator;
import com.mashreq.conference.persistence.entity.Booking;
import com.mashreq.conference.ports.inbound.BookingController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/conference-rooms")
@RequiredArgsConstructor
@Slf4j
public class InboundBookingControllerAdapter implements BookingController {

    private final BookingService bookingService;

    private final BookingValidator bookingValidator;

    @InitBinder("bookingRequest")
    protected void initBookingRequest(WebDataBinder binder) {
        binder.addValidators(bookingValidator);
    }

    @Override
    @Operation(summary = "Book room")
    @ApiResponse(responseCode = "200", description = "Room booked successfully.", content = @Content(mediaType = "application/json"))
    @PostMapping("/{roomId}/book-room")
    public ResponseEntity<Response<BookingResponse>> bookRoom(@PathVariable String roomId, @Valid @RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok()
                .body(Response.<BookingResponse>builder()
                        .data(bookingService.createBooking(Long.valueOf(roomId), bookingRequest))
                        .message("Room booked successfully.")
                        .status(ResponseStatus.SUCCESS)
                        .build());
    }

    @Override
    public ResponseEntity<List<ConferenceRoomReq>> getAvailableRooms(LocalTime startTime, LocalTime endTime) {
        return null;
    }
    // Inject BookingService and delegate calls
}