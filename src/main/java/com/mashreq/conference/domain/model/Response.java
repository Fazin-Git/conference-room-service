package com.mashreq.conference.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class Response<T> {
    private ResponseStatus status;
    private String message;
    private T data;
    private String errorCode;
    private String errorDetails;
    private String uriPath;
    private String trace;
}