package com.mashreq.conference.infra.exceptions;

public record ViolationDto(String fieldName, String message) {

    public String toString() {
        String var10000 = this.fieldName();
        return "ViolationDto(fieldName=" + var10000 + ", message=" + this.message() + ")";
    }
}