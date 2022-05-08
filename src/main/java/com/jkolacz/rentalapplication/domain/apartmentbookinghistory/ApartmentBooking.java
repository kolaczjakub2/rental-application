package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
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

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public BookingStep getStep() {
        return step;
    }

    public void setStep(BookingStep step) {
        this.step = step;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public BookingPeriod getBookingPeriod() {
        return bookingPeriod;
    }

    public void setBookingPeriod(BookingPeriod bookingPeriod) {
        this.bookingPeriod = bookingPeriod;
    }
}
