package com.mashreq.conference.adapters.inbound;

import com.mashreq.conference.domain.model.ConferenceRoom;
import com.mashreq.conference.domain.service.ConferenceRoomService;
import com.mashreq.conference.ports.inbound.ConferenceRoomController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conference-rooms")
@RequiredArgsConstructor
public class InboundConferenceRoomControllerAdapter implements ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @Override
    @Operation(summary = "Get all conference room details")
    @ApiResponse(responseCode = "200", description = "All conference room fetched successfully.", content = @Content(mediaType = "application/json"))
    @GetMapping("/all-rooms")
    public ResponseEntity<List<ConferenceRoom>> getAllRooms() {
        List<ConferenceRoom> rooms = conferenceRoomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Get conference room details")
    @ApiResponse(responseCode = "200", description = "conference room fetched successfully.", content = @Content(mediaType = "application/json"))
    @GetMapping("/{roomId}")
    public ResponseEntity<ConferenceRoom> getRoomById(Long roomId) {
        return null;
    }

    // Inject ConferenceRoomService and delegate calls
}