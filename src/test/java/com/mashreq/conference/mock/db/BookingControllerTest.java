package com.mashreq.conference.mock.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashreq.conference.domain.model.*;
import com.mashreq.conference.domain.service.impl.UserService;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;


@ExtendWith(MockitoExtension.class)
class BookingControllerTest extends BaseControllerTest{
    @Autowired
    public
    MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public UserService userService;
    @Test
    @DisplayName("Create booking by passing start,end time,room id and number of people attending ")
    @Order(1)
    @SneakyThrows
    void testBookingSuccess() {
        BookingRequest bookingRequest = new BookingRequest(2L,LocalDateTime.now().plusHours(1).withMinute(15),LocalDateTime.now().plusHours(1).withMinute(30),7);
        userService.signup(new SignupRequest("Fasin6","fasin6@mashreq.com","12345"));
        LoginResponse login = userService.login(new LoginRequest("fasin6@mashreq.com", "12345"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(String.format("/conference-rooms/%s/book", 2))
                .content(objectMapper.writeValueAsString(bookingRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + login.token())
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Response<String> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.SUCCESS);
    }

    @Test
    @DisplayName("Create booking by passing start,end time and number of people attending ")
    @Order(2)
    @SneakyThrows
    void testBookingSuccessByNumberOfParticipants() {
        BookingRequest bookingRequest = BookingRequest.builder()
                .startTime(LocalDateTime.now().plusHours(2).withMinute(15))
                .endTime(LocalDateTime.now().plusHours(2).withMinute(30))
                .numOfPeople(7)
                .build();
        userService.signup(new SignupRequest("Fasin2","fasin2@mashreq.com","12345"));
        LoginResponse login = userService.login(new LoginRequest("fasin2@mashreq.com", "12345"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/conference-rooms/book")
                .content(objectMapper.writeValueAsString(bookingRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + login.token())
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Response<String> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.SUCCESS);
    }

    @Test
    @DisplayName("Get all current bookings")
    @SneakyThrows
    @Order(3)
    void testGetApplicationDetails() {
        userService.signup(new SignupRequest("Fasin3","fasin3@mashreq.com","12345"));
        LoginResponse login = userService.login(new LoginRequest("fasin3@mashreq.com", "12345"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/conference-rooms/current-bookings")
                .header("Authorization", "Bearer " + login.token())
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Response<String> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.SUCCESS);
    }

    @Test
    @DisplayName("Get all current bookings by room id")
    @SneakyThrows
    @Order(4)
    void testGetAllBookingsByRoomId() {
        userService.signup(new SignupRequest("Fasin4","fasin4@mashreq.com","12345"));
        LoginResponse login = userService.login(new LoginRequest("fasin4@mashreq.com", "12345"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/conference-rooms/%s/bookings", 2))
                .header("Authorization", "Bearer " + login.token())
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Response<String> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.SUCCESS);
    }
    @Test
    @DisplayName("Create booking by passing wrong start and end time and throw exception ")
    @Order(5)
    @SneakyThrows
    void testBookingWithException() {
        BookingRequest bookingRequest = new BookingRequest(2L,LocalDateTime.now().plusHours(2).withMinute(15),LocalDateTime.now().plusHours(1).withMinute(30),7);
        userService.signup(new SignupRequest("Fasin6","1fasin6@mashreq.com","12345"));
        LoginResponse login = userService.login(new LoginRequest("1fasin6@mashreq.com", "12345"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(String.format("/conference-rooms/%s/book", 2))
                .content(objectMapper.writeValueAsString(bookingRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + login.token())
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Response<String> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.ERROR);
    }
}