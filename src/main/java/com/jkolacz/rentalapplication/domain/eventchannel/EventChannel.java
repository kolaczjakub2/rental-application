package com.jkolacz.rentalapplication.domain.eventchannel;

import com.jkolacz.rentalapplication.domain.apartment.ApartmentBooked;
import com.jkolacz.rentalapplication.domain.booking.BookingAccepted;
import com.jkolacz.rentalapplication.domain.hotelroom.HotelRoomBooked;

public interface EventChannel {
    void publish(ApartmentBooked apartmentBooked);

    void publish(HotelRoomBooked hotelRoomBooked);

    void publish(BookingAccepted bookingAccepted);
}
