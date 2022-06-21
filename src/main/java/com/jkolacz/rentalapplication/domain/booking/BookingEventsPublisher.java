package com.jkolacz.rentalapplication.domain.booking;

import com.jkolacz.rentalapplication.domain.clock.Clock;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

import java.time.LocalDate;
import java.util.List;

public class BookingEventsPublisher {
    private final EventIdFactory eventIdFactory;
    private final Clock clock;
    private final EventChannel eventChannel;

    public BookingEventsPublisher(EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        this.eventIdFactory = eventIdFactory;
        this.clock = clock;
        this.eventChannel = eventChannel;
    }

    void bookingAccepted(RentalType rentalType, String rentalPlaceId, String tenantId, List<LocalDate> days) {
        BookingAccepted bookingAccepted = new BookingAccepted(eventIdFactory.create(), clock.now(), rentalType.name(), rentalPlaceId, tenantId, days);
        eventChannel.publish(bookingAccepted);
    }
}
