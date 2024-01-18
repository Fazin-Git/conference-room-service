package com.mashreq.conference.domain.model;

import com.mashreq.conference.infra.validator.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
    @NotBlank(message = "Name cannot be blank")
    String name,

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    String email,

    @NotBlank(message = "Password cannot be blank")
    @Password
    String password) {
}