package com.jkolacz.rentalapplication.domain.booking;

import java.util.List;

public interface BookingRepository {
    String save(Booking booking);

    Booking findById(String bookingId);

    List<Booking> findAllBy(RentalPlaceIdentifier identifier);

    List<Booking> findAllAcceptedBy(RentalPlaceIdentifier identifier);
}
