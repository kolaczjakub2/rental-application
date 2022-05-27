package com.jkolacz.rentalapplication.domain.apartment;

import com.jkolacz.rentalapplication.domain.event.EventIdFactory;

public class ApartmentEventsPublisher {
    private EventIdFactory eventIdFactory;
    private com.jkolacz.rentalapplication.domain.eventchannel.ApartmentEventsPublisher eventChannel;

    public ApartmentEventsPublisher(EventIdFactory eventIdFactory, com.jkolacz.rentalapplication.domain.eventchannel.ApartmentEventsPublisher eventChannel) {
        this.eventIdFactory = eventIdFactory;
        this.eventChannel = eventChannel;
    }

    public void publishApartmentBooked(String id, String ownerId, String tenantId, Period period) {
        ApartmentBooked apartmentBooked = ApartmentBooked.create(eventIdFactory.create(), id, ownerId, tenantId, period);
        eventChannel.publish(apartmentBooked);
    }
}
