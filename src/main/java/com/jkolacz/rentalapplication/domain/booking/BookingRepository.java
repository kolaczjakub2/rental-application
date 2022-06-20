package com.jkolacz.rentalapplication.domain.booking;

public interface BookingRepository {
    String save(Booking booking);

    Booking findById(String bookingId);
}
