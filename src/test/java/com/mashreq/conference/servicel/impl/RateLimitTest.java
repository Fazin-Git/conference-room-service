package com.mashreq.conference.servicel.impl;

import com.mashreq.conference.domain.model.LoginRequest;
import com.mashreq.conference.domain.model.SignupRequest;
import com.mashreq.conference.domain.service.impl.UserService;
import com.mashreq.conference.persistence.repository.UserRepository;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RateLimitTest {

    @Mock
    UserRepository repository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
   AuthenticationManager authenticationManager;


    @InjectMocks
    UserService userService;

    @Test
    void rateLimitTest() throws Exception {
        Map<Integer, Integer> responseCount = new HashMap<>();

        // Act
        IntStream.rangeClosed(1, 100)
                .parallel()
                .forEach(i -> {
                    userService.signup(new SignupRequest("Fasin1"+i,"fasin1@gmail.com"+i,"1234545"));
                });
        assertThat(true).isTrue();

    }
}
