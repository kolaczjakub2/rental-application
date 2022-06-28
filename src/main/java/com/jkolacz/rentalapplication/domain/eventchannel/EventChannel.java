package com.jkolacz.rentalapplication.domain.eventchannel;

import com.jkolacz.rentalapplication.domain.apartment.ApartmentBooked;
import com.jkolacz.rentalapplication.domain.booking.BookingAccepted;
import com.jkolacz.rentalapplication.domain.booking.BookingRejected;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomBooked;

public interface EventChannel {
    void publish(ApartmentBooked apartmentBooked);

    void publish(HotelRoomBooked hotelRoomBooked);

    void publish(BookingAccepted bookingAccepted);

    void publish(BookingRejected bookingRejected);
}
