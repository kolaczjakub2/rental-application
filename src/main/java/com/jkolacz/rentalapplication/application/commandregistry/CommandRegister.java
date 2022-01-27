package com.jkolacz.rentalapplication.application.commandregistry;

import com.jkolacz.rentalapplication.application.booking.BookingReject;

public interface CommandRegister {
    void register(BookingReject bookingReject);
}
