package com.mashreq.conference.adapters.inbound;

import com.mashreq.conference.domain.model.ConferenceRoomRes;
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
public class InboundConferenceRoomControllerAdapter implements ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @Override
    @Operation(summary = "Get all available conference room for the time frame")
    @ApiResponse(responseCode = "200", description = "All available room's fetched successfully.", content = @Content(mediaType = "application/json"))
    @GetMapping("/available")
    public ResponseEntity<List<ConferenceRoomRes>> getAvailableRooms(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        List<ConferenceRoomRes> rooms = conferenceRoomService.getAvailableRooms(startTime,endTime);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Get all conference rooms")
    @ApiResponse(responseCode = "200", description = "conference room fetched successfully.", content = @Content(mediaType = "application/json"))
    @GetMapping
    public ResponseEntity<List<ConferenceRoomRes>> getAllRooms() {
        List<ConferenceRoomRes> rooms = conferenceRoomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}