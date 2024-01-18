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
class ConferenceRoomControllerTest extends BaseControllerTest{

    @Autowired
    public
    MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public UserService userService;


    @Test
    @DisplayName("Get all the available conference room between two time frames")
    @SneakyThrows
    @Order(1)
    void testGetAvailableRoomsForATimeFrame() {
        userService.signup(new SignupRequest("Fasin","fasin1@mashreq.com","12345"));
        LoginResponse login = userService.login(new LoginRequest("fasin1@mashreq.com", "12345"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(String.format("/conference-rooms/available?startTime=%s&endTime=%s", LocalDateTime.now().plusHours(1).withMinute(15),LocalDateTime.now().plusHours(1).withMinute(30)))
                .header("Authorization", "Bearer " + login.token())
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Response<String> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.SUCCESS);
    }

    @Test
    @DisplayName("Get all rooms")
    @SneakyThrows
    @Order(2)
    void getAllRooms() {
        userService.signup(new SignupRequest("Fasin5","fasin5@mashreq.com","12345"));
        LoginResponse login = userService.login(new LoginRequest("fasin5@mashreq.com", "12345"));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/conference-rooms")
                .header("Authorization", "Bearer " + login.token())
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Response<String> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.SUCCESS);
    }
}
