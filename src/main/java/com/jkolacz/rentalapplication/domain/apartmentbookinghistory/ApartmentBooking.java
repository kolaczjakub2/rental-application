package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

public class ApartmentBooking {

    private final BookingStep step;
    private final String ownerId;
    private final String tenantId;
    private final BookingPeriod bookingPeriod;

    public ApartmentBooking(BookingStep step, String ownerId, String tenantId, BookingPeriod bookingPeriod) {

        this.step = step;
        this.ownerId = ownerId;
        this.tenantId = tenantId;
        this.bookingPeriod = bookingPeriod;
    }

    public static ApartmentBooking start(String ownerId, String tenantId, BookingPeriod bookingPeriod) {
        return new ApartmentBooking(BookingStep.START, ownerId, tenantId, bookingPeriod);
    }



}
