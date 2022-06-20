package com.jkolacz.rentalapplication.domain.apartment;

import com.jkolacz.rentalapplication.domain.apartment.ApartmentBooked;
import com.jkolacz.rentalapplication.domain.apartment.Period;

import java.time.LocalDateTime;

public class ApartmentBookedTestFactory {
    public static ApartmentBooked create(String eventId, LocalDateTime eventCreationDateTime, String apartmentId, String ownerId, String tenantId, Period period) {
        return new ApartmentBooked(eventId, eventCreationDateTime, apartmentId, ownerId, tenantId, period);
    }
}