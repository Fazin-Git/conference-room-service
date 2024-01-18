package com.mashreq.conference.adapters.inbound;


import com.mashreq.conference.domain.model.*;
import com.mashreq.conference.domain.service.impl.UserService;
import com.mashreq.conference.ports.inbound.LoginController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class LoginControllerAdapter implements LoginController {

    private final UserService userService;

    @Override
    @Operation(summary = "Login to Application")
    @ApiResponse(responseCode = "200", description = "Login successful.", content = @Content(mediaType = "application/json"))
    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok()
                .body(Response.<LoginResponse>builder()
                        .data(userService.login(loginRequest))
                        .message("Login successful.")
                        .status(ResponseStatus.SUCCESS)
                        .build());
    }

    @Override
    @Operation(summary = "Signup to Application")
    @ApiResponse(responseCode = "200", description = "Sign-up successful.", content = @Content(mediaType = "application/json"))
    @PostMapping("/sign-up")
    public ResponseEntity<Response<String>> signup(@RequestBody SignupRequest signupRequest) {
         userService.signup(signupRequest);
        return ResponseEntity.ok()
                .body(Response.<String>builder()
                        .message("Sign-up successful.")
                        .status(ResponseStatus.SUCCESS)
                        .build());
    }
}
