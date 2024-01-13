package com.mashreq.conference.domain.service.impl;

import com.mashreq.conference.domain.model.BookingDTO;
import com.mashreq.conference.domain.service.BookingService;
import com.mashreq.conference.infra.config.MaintenanceConfiguration;
import com.mashreq.conference.persistence.entity.Booking;
import com.mashreq.conference.persistence.entity.ConferenceRoom;
import com.mashreq.conference.persistence.repository.BookingRepository;
import com.mashreq.conference.persistence.repository.ConferenceRoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final ConferenceRoomRepository conferenceRoomRepository;

    private final MaintenanceConfiguration maintenanceConfiguration;


    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, ConferenceRoomRepository conferenceRoomRepository, MaintenanceConfiguration maintenanceConfiguration) {
        this.bookingRepository = bookingRepository;
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.maintenanceConfiguration = maintenanceConfiguration;
    }

    @Override
    public boolean isBookingAllowed(Long roomId, LocalDateTime startTime, LocalDateTime endTime, int numberOfPeople) {
        return bookingRepository.hasOverlappingBookings(roomId,startTime,endTime);
    }

    @Override
    public Booking createBooking(Long roomId, BookingDTO bookingDTO) throws Exception {
        ConferenceRoom room = conferenceRoomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Conference Room not found"));
        validateBookingRequest(bookingDTO.getStartTime(), bookingDTO.getEndTime(), bookingDTO.getNumOfPeople(), room);
        boolean isBookingOverlapped = bookingRepository.hasOverlappingBookings(roomId, bookingDTO.getStartTime(), bookingDTO.getEndTime());
        log.info("Overlap booking status : {} for room id {}",isBookingOverlapped,roomId);
        if (isBookingOverlapped) {
            throw new RuntimeException("Booking overlaps with existing bookings");
        }
        Booking booking = new Booking();
        booking.setConferenceRoom(room);
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());
        booking.setNumOfPeople(bookingDTO.getNumOfPeople());
        return bookingRepository.save(booking);
    }
    private void validateBookingRequest(LocalDateTime startTime, LocalDateTime endTime, int numOfPeople, ConferenceRoom room) throws Exception {
        if (startTime.toLocalDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("Booking can only be done for the current date");
        }

        if (startTime.getMinute() % 15 != 0 || endTime.getMinute() % 15 != 0) {
            throw new RuntimeException("Booking should be in intervals of 15 minutes");
        }

        // Validate maintenance time
        if(validateMaintenanceTime(startTime, endTime)){
            throw new RuntimeException("Booking cannot be done during maintenance time");
        }

        if (numOfPeople <= 1 || numOfPeople > room.getMaxCapacity()) {
            throw new RuntimeException("Number of people should be greater than 1 and less than or equal to the maximum capacity of the room"+room.getMaxCapacity());
        }
    }
    private boolean isDuringMaintenance(LocalDateTime time, LocalTime maintenanceStart, LocalTime maintenanceEnd) {
        LocalTime bookingTime = time.toLocalTime();
        return !bookingTime.isBefore(maintenanceStart) && !bookingTime.isAfter(maintenanceEnd);
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
}
