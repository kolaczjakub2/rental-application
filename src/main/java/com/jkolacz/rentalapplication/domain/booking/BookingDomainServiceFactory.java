package com.jkolacz.rentalapplication.domain.booking;

import com.jkolacz.rentalapplication.domain.clock.Clock;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

public class BookingDomainServiceFactory {
    public BookingDomainService create(EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        BookingEventsPublisher bookingEventsPublisher = new BookingEventsPublisher(eventIdFactory, clock, eventChannel);
        return new BookingDomainService(bookingEventsPublisher);
    }
}
