package com.mashreq.conference.domain.service.impl;

import java.util.Optional;
import java.util.function.Function;

import com.mashreq.conference.domain.model.LoginRequest;
import com.mashreq.conference.domain.model.LoginResponse;
import com.mashreq.conference.domain.model.SignupRequest;
import com.mashreq.conference.domain.model.User;
import com.mashreq.conference.infra.config.JwtHelper;
import com.mashreq.conference.infra.exceptions.ConferenceRoomErrorCode;
import com.mashreq.conference.infra.exceptions.ConferenceRoomException;
import com.mashreq.conference.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @RateLimiter(name = "default")
  @Transactional
  public void signup(SignupRequest request) {
    String email = request.email();
    Optional<User> existingUser = repository.findByEmail(email).map(toDomain());
    if (existingUser.isPresent()) {
      throw new ConferenceRoomException(ConferenceRoomErrorCode.E_USER_ALREADY_PRESENT);
    }
    String hashedPassword = passwordEncoder.encode(request.password());
    com.mashreq.conference.persistence.entity.User user = new com.mashreq.conference.persistence.entity.User();
    user.setUsername(request.name());
    user.setEmail(email);
    user.setPassword(hashedPassword);
    log.info("Sign-up");
    repository.save(user);
  }

  private static Function<com.mashreq.conference.persistence.entity.User, User> toDomain() {
    return val -> new User(val.getUsername(), val.getEmail(), val.getPassword());
  }

  public LoginResponse login(LoginRequest loginRequest) {
    authenticate(loginRequest.email(), loginRequest.password());
    String token = JwtHelper.generateToken(loginRequest.email());

    return new LoginResponse(loginRequest.email(), token);
  }

  private void authenticate(String email, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    } catch (DisabledException e) {
      throw new ConferenceRoomException(ConferenceRoomErrorCode.E_USER_DO_NOT_EXIST);
    } catch (BadCredentialsException e) {
      throw new ConferenceRoomException(ConferenceRoomErrorCode.E_INVALID_CREDENTIALS);
    }
  }
}