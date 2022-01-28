package com.jkolacz.rentalapplication.domain.apartment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BookingAccepted {
    private final String eventId;
    private final LocalDateTime eventCreationDateTime;
    private final String name;
    private final String rentalPlaceId;
    private final String tenantId;
    private final List<LocalDate> days;

    public BookingAccepted(String eventId, LocalDateTime eventCreationDateTime, String name, String rentalPlaceId, String tenantId, List<LocalDate> days) {

        this.eventId = eventId;
        this.eventCreationDateTime = eventCreationDateTime;
        this.name = name;
        this.rentalPlaceId = rentalPlaceId;
        this.tenantId = tenantId;
        this.days = days;
    }

    public static BookingAccepted create(RentalType rentalType, String rentalPlaceId, String tenantId, List<LocalDate> days) {
        String eventId= UUID.randomUUID().toString();
        LocalDateTime eventCreationDateTime = LocalDateTime.now();
        return new BookingAccepted(eventId,eventCreationDateTime, rentalType.name(),rentalPlaceId,tenantId,days);
    }

    public String getEventId() {
        return eventId;
    }

    public LocalDateTime getEventCreationDateTime() {
        return eventCreationDateTime;
    }

    public String getName() {
        return name;
    }

    public String getRentalPlaceId() {
        return rentalPlaceId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public List<LocalDate> getDays() {
        return days;
    }
}
