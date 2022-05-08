package com.jkolacz.rentalapplication.application.commandregistry;

import com.jkolacz.rentalapplication.application.booking.BookingAccept;
import com.jkolacz.rentalapplication.application.booking.BookingReject;

public interface CommandRegistry {
    void register(BookingReject bookingReject);

    void register(BookingAccept bookingAccept);
}
