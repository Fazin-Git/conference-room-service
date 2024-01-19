package com.mashreq.conference.mock.db;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashreq.conference.domain.model.LoginRequest;
import com.mashreq.conference.domain.model.Response;
import com.mashreq.conference.domain.model.ResponseStatus;
import com.mashreq.conference.domain.model.SignupRequest;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class LoginControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("Sign-up test")
    @SneakyThrows
    @Order(1)
    void signUpTest() {
        SignupRequest signupRequest = new SignupRequest("Fasin10","fasin10@mashreq.com","Mashreq@123");
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
        LoginRequest loginRequest = new LoginRequest("fasin10@mashreq.com", "Mashreq@123");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/auth/login")
                .content(objectMapper.writeValueAsString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        Response<String> response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
        Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.SUCCESS);
    }

    //@Test
    //Enable Rate limit test case only for testing since test case files execution is in parallel and junit doesn't support class ordering.
    //Rate limiting should be tested using Jmeter.
    void rateLimitTest() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> IntStream.rangeClosed(1, 100)
                        .parallel()
                        .forEach(i -> {
                            SignupRequest signupRequest = new SignupRequest("Fasin12"+i,"fasin7@mashreq.com"+i,"Mashreq@123");
                            MvcResult mvcResult;
                            try {
                                mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                        .post("/auth/sign-up")
                                        .content(objectMapper.writeValueAsString(signupRequest))
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)).andReturn();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }

                            Response<String> response;
                            try {
                                response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Response.class);
                            } catch (JsonProcessingException | UnsupportedEncodingException e) {
                                throw new RuntimeException(e);
                            }
                            Assertions.assertThat(response.getStatus()).isEqualTo(ResponseStatus.SUCCESS);
                        }),
                "Expected to throw, but it didn't");
        assertThat(thrown.getMessage()).contains("RequestNotPermitted: RateLimiter 'default' does not permit further calls");

    }

}
