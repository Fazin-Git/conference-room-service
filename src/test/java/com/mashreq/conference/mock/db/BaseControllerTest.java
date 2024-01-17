package com.mashreq.conference.mock.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashreq.conference.ConferenceRoomServiceApplication;
import com.mashreq.conference.domain.model.*;
import com.mashreq.conference.domain.service.impl.UserService;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {ConferenceRoomServiceApplication.class})
@ExtendWith(MockitoExtension.class)
class BaseControllerTest {
    @Autowired
    public
    MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public UserService userService;
    @Test
    @DisplayName("Create booking")
    @SneakyThrows
    @Order(1)
    void testBookingSuccess() {
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setNumOfPeople(7);
        bookingRequest.setStartTime(LocalDateTime.now().withMinute(15));
        bookingRequest.setEndTime(LocalDateTime.now().withMinute(30));
        bookingRequest.setRoomId(2L);
        userService.signup(new SignupRequest("Fasin","fasinmu@mashreq.com","12345"));
        LoginResponse login = userService.login(new LoginRequest("fasinmu@mashreq.com", "12345"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(String.format("/conference-rooms/%s/book-room", 2))
                .content(objectMapper.writeValueAsString(bookingRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + login.token())
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Response<String> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.SUCCESS);
    }

}