package com.jkolacz.rentalapplication.domain.eventchannel;

import com.jkolacz.rentalapplication.domain.apartment.ApartmentBooked;
import com.jkolacz.rentalapplication.domain.apartment.BookingAccepted;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomBooked;

public interface ApartmentEventsPublisher {
    void publish(ApartmentBooked apartmentBooked);

    void publish(HotelRoomBooked hotelRoomBooked);

    void publish(BookingAccepted bookingAccepted);
}
