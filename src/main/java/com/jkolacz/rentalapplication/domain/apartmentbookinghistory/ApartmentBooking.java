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

    private LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    private void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    private BookingStep getStep() {
        return step;
    }

    private void setStep(BookingStep step) {
        this.step = step;
    }

    private String getOwnerId() {
        return ownerId;
    }

    private void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    private String getTenantId() {
        return tenantId;
    }

    private void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    private BookingPeriod getBookingPeriod() {
        return bookingPeriod;
    }

    private void setBookingPeriod(BookingPeriod bookingPeriod) {
        this.bookingPeriod = bookingPeriod;
    }
}
