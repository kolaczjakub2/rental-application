package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

import java.time.LocalDateTime;

public class ApartmentBooking {

    private final LocalDateTime bookingDateTime;
    private final BookingStep step;
    private final String ownerId;
    private final String tenantId;
    private final BookingPeriod bookingPeriod;

    public ApartmentBooking(BookingStep step, LocalDateTime bookingDateTime, String ownerId, String tenantId, BookingPeriod bookingPeriod) {
        this.bookingDateTime = bookingDateTime;
        this.step = step;
        this.ownerId = ownerId;
        this.tenantId = tenantId;
        this.bookingPeriod = bookingPeriod;
    }

    public static ApartmentBooking start(LocalDateTime bookingDateTime, String ownerId, String tenantId, BookingPeriod bookingPeriod) {
        return new ApartmentBooking(BookingStep.START, bookingDateTime, ownerId, tenantId, bookingPeriod);
    }


}
