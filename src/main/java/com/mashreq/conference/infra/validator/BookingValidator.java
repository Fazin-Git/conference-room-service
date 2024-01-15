package com.mashreq.conference.infra.validator;

import com.mashreq.conference.domain.model.BookingRequest;
import com.mashreq.conference.infra.config.MaintenanceConfiguration;
import com.mashreq.conference.infra.exceptions.ConferenceRoomException;
import com.mashreq.conference.infra.exceptions.ConferenceRoomErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@AllArgsConstructor
public class BookingValidator implements Validator {

    private final MaintenanceConfiguration maintenanceConfiguration;

    @Override
    public boolean supports(Class<?> aClass) {
        return BookingRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BookingRequest bookingRequest = (BookingRequest) target;
        if (bookingRequest.getStartTime().toLocalDate().isAfter(LocalDate.now()) ||
                bookingRequest.getStartTime().toLocalDate().isBefore(LocalDate.now())) {
            throw new ConferenceRoomException(ConferenceRoomErrorCode.E_INVALID_BOOKING_DATE);
        }

        if (bookingRequest.getStartTime().getMinute() % 15 != 0 || bookingRequest.getEndTime().getMinute() % 15 != 0) {
            throw new ConferenceRoomException(ConferenceRoomErrorCode.E_BOOKING_INTERVAL_NOT_ALLOWED);
        }

        // Validate maintenance time
        if(validateMaintenanceTime(bookingRequest.getStartTime(), bookingRequest.getEndTime())){
            throw new ConferenceRoomException(ConferenceRoomErrorCode.E_UNDER_MAINTENANCE_TIME);
        }
    }
    public boolean validateMaintenanceTime(LocalDateTime startTime, LocalDateTime endTime) {
        List<MaintenanceConfiguration.MaintenanceSlot> maintenanceSlots = maintenanceConfiguration.getSlots();
        for (MaintenanceConfiguration.MaintenanceSlot slot : maintenanceSlots) {
            if (isDuringMaintenance(startTime,slot.getStart(),slot.getEnd()) || isDuringMaintenance(endTime,slot.getStart(),slot.getEnd())) {
                return true;
            }
        }
        return false;
    }
    private boolean isDuringMaintenance(LocalDateTime time, LocalTime maintenanceStart, LocalTime maintenanceEnd) {
        LocalTime bookingTime = time.toLocalTime();
        return !bookingTime.isBefore(maintenanceStart) && !bookingTime.isAfter(maintenanceEnd);
    }
    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
