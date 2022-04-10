package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
public class ApartmentBooking {

    private LocalDateTime bookingDateTime;
    @Enumerated(EnumType.STRING)
    private BookingStep step;
    private String ownerId;
    private String tenantId;
    @Embedded
    private BookingPeriod bookingPeriod;

    public ApartmentBooking() {
    }

    ApartmentBooking(BookingStep step, LocalDateTime bookingDateTime, String ownerId, String tenantId, BookingPeriod bookingPeriod) {
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
