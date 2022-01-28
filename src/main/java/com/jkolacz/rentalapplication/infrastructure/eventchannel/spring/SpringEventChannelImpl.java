package com.jkolacz.rentalapplication.infrastructure.eventchannel.spring;

import com.jkolacz.rentalapplication.domain.apartment.ApartmentBooked;
import com.jkolacz.rentalapplication.domain.apartment.BookingAccepted;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomBooked;
import org.springframework.context.ApplicationEventPublisher;

class SpringEventChannelImpl implements EventChannel {

    private final ApplicationEventPublisher publisher;

    SpringEventChannelImpl(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(ApartmentBooked apartmentBooked) {
        publisher.publishEvent(apartmentBooked);

    }

    @Override
    public void publish(HotelRoomBooked hotelRoomBooked) {
        publisher.publishEvent(hotelRoomBooked);
    }

    @Override
    public void publish(BookingAccepted bookingAccepted) {
        publisher.publishEvent(bookingAccepted);
    }
}
