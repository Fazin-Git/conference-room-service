package com.mashreq.conference.mock.db;


import com.mashreq.conference.domain.model.LoginRequest;
import com.mashreq.conference.domain.model.Response;
import com.mashreq.conference.domain.model.ResponseStatus;
import com.mashreq.conference.domain.model.SignupRequest;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@ExtendWith(MockitoExtension.class)
class LoginControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("Sign-up test")
    @SneakyThrows
    @Order(1)
    void signUpTest() {
        SignupRequest signupRequest = new SignupRequest("Fasin4","fasin4@mashreq.com","12345");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/auth/sign-up")
                .content(objectMapper.writeValueAsString(signupRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Response<String> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.SUCCESS);
    }
    @Test
    @DisplayName("Login test")
    @SneakyThrows
    @Order(2)
    void LoginTest() {
        LoginRequest loginRequest = new LoginRequest("fasin4@mashreq.com", "12345");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/auth/login")
                .content(objectMapper.writeValueAsString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Response<String> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.SUCCESS);
    }
}
