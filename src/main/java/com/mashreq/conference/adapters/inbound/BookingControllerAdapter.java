package com.mashreq.conference.adapters.inbound;

import com.mashreq.conference.domain.model.*;
import com.mashreq.conference.domain.model.ResponseStatus;
import com.mashreq.conference.domain.service.BookingService;
import com.mashreq.conference.infra.validator.BookingValidator;
import com.mashreq.conference.ports.inbound.BookingController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conference-rooms")
@RequiredArgsConstructor
@Slf4j
public class BookingControllerAdapter implements BookingController {

    private final BookingService bookingService;

    private final BookingValidator bookingValidator;

    @InitBinder("bookingRequest")
    protected void initBookingRequest(WebDataBinder binder) {
        binder.addValidators(bookingValidator);
    }

    @Override
    @Operation(summary = "Book room by passing room id")
    @ApiResponse(responseCode = "200", description = "Room booked successfully.", content = @Content(mediaType = "application/json"))
    @PostMapping("/{roomId}/book")
    public ResponseEntity<Response<BookingResponse>> bookRoomById(@PathVariable String roomId, @Valid @RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok()
                .body(Response.<BookingResponse>builder()
                        .data(bookingService.createBooking(Long.valueOf(roomId), bookingRequest))
                        .message("Room booked successfully.")
                        .status(ResponseStatus.SUCCESS)
                        .build());
    }

    @Override
    @Operation(summary = "Find and book room by number of people")
    @ApiResponse(responseCode = "200", description = "Room booked successfully.", content = @Content(mediaType = "application/json"))
    @PostMapping("/book")
    public ResponseEntity<Response<BookingResponse>> bookRoomByNumberOfParticipants(@Valid @RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok()
                .body(Response.<BookingResponse>builder()
                        .data(bookingService.bookRoomByNumberOfParticipants(bookingRequest))
                        .message("Room booked successfully.")
                        .status(ResponseStatus.SUCCESS)
                        .build());
    }

    @Override
    @Operation(summary = "Get all bookings for the day")
    @ApiResponse(responseCode = "200", description = "All bookings fetched successfully.", content = @Content(mediaType = "application/json"))
    @GetMapping("/current-bookings")
    public ResponseEntity<Response<List<BookingResponse>>> getAllBookings() {
        return ResponseEntity.ok()
                .body(Response.<List<BookingResponse>>builder()
                        .data(bookingService.getAllBookings())
                        .message("All bookings fetched successfully.")
                        .status(ResponseStatus.SUCCESS)
                        .build());
    }
}