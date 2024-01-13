package com.mashreq.conference.infra.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorData {
    private String errorId;
    private Object errorMessage;
    private Object errorDetails;

    public ErrorData() {
    }

    public ErrorData(String errorId, Object errorMessage) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
    }

    public ErrorData(String errorId, Object errorMessage, Object errorDetails) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }

}
