package com.jkolacz.rentalapplication.application.booking;

public class BookingAccept {
    private final String bookingId;

    public BookingAccept(String bookingId) {
        this.bookingId = bookingId;
    }

    String getBookingId() {
        return bookingId;
    }
}
