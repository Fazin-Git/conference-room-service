package com.mashreq.conference.infra.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Configuration
public class SecurityProperties {

    @Value("${app.security.jwt-key}")
    private String key;
    @Value("${app.security.validity-customer}")
    private long customerTokenValidity;

    @Value("${app.security.permit-uris}")
    private List<String> permitUris;
}
