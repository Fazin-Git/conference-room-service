package com.mashreq.conference.ports.inbound;

import com.mashreq.conference.domain.model.LoginRequest;
import com.mashreq.conference.domain.model.LoginResponse;
import com.mashreq.conference.domain.model.Response;
import com.mashreq.conference.domain.model.SignupRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface LoginController {

    ResponseEntity<Response<LoginResponse>> login(@RequestBody LoginRequest loginRequest);

    ResponseEntity<Response<String>> signup(SignupRequest signupRequest);
}
