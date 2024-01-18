package com.mashreq.conference.adapters.inbound;

import com.mashreq.conference.domain.model.BookingResponse;
import com.mashreq.conference.domain.model.ConferenceRoomRes;
import com.mashreq.conference.domain.model.Response;
import com.mashreq.conference.domain.model.ResponseStatus;
import com.mashreq.conference.domain.service.ConferenceRoomService;
import com.mashreq.conference.ports.inbound.ConferenceRoomController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/conference-rooms")
@RequiredArgsConstructor
public class ConferenceRoomControllerAdapter implements ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @Override
    @Operation(summary = "Get all available conference room for the time frame")
    @ApiResponse(responseCode = "200", description = "All available room's fetched successfully.", content = @Content(mediaType = "application/json"))
    @GetMapping("/available")
    public ResponseEntity<Response<List<ConferenceRoomRes>>> getAvailableRooms(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return ResponseEntity.ok()
                .body(Response.<List<ConferenceRoomRes>>builder()
                        .data(conferenceRoomService.getAvailableRooms(startTime,endTime))
                        .message("Available rooms fetched successfully.")
                        .status(ResponseStatus.SUCCESS)
                        .build());
    }

    @Override
    @Operation(summary = "Get all conference rooms")
    @ApiResponse(responseCode = "200", description = "conference room fetched successfully.", content = @Content(mediaType = "application/json"))
    @GetMapping
    public ResponseEntity<Response<List<ConferenceRoomRes>>> getAllRooms() {
        return ResponseEntity.ok()
                .body(Response.<List<ConferenceRoomRes>>builder()
                        .data(conferenceRoomService.getAllRooms())
                        .message("All rooms fetched successfully.")
                        .status(ResponseStatus.SUCCESS)
                        .build());
    }
}