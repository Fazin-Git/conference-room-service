package com.mashreq.conference.infra.exceptions;

import com.mashreq.conference.domain.model.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BookingErrorResponse implements Serializable {
    boolean hasError;
    private ResponseStatus status;
    private ErrorData data;
}