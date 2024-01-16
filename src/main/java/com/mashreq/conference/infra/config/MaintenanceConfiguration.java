package com.mashreq.conference.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;


@Component
@ConfigurationProperties(prefix = "maintenance")
public class MaintenanceConfiguration {

private List<MaintenanceSlot> slots;

public List<MaintenanceSlot> getSlots() {
    return slots;
}

public void setSlots(List<MaintenanceSlot> slots) {
    this.slots = slots;
}

public static class MaintenanceSlot {
    private LocalTime start;
    private LocalTime end;

    public MaintenanceSlot(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
}

// Custom converter for LocalTime
@Component
public static class LocalTimeConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String source) {
        return LocalTime.parse(source);
    }
}

}
