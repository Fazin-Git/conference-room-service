package com.mashreq.conference.infra.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDto {
    List<ViolationDto> violations = new ArrayList<>();

    public List<ViolationDto> getViolations() {
        return this.violations;
    }

    public ValidationErrorDto() {
    }

    public String toString() {
        return "ValidationErrorDto(violations=" + this.getViolations() + ")";
    }
}
