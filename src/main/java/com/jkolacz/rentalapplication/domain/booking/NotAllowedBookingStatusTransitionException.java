package com.jkolacz.rentalapplication.domain.booking;

class NotAllowedBookingStatusTransitionException extends RuntimeException {
    NotAllowedBookingStatusTransitionException(BookingStatus from, BookingStatus to) {
        super("Not allowed transition from " + from + " to " + to + " booking");
    }
}
