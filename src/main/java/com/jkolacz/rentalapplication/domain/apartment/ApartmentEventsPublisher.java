package com.jkolacz.rentalapplication.domain.apartment;

import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

public class ApartmentEventsPublisher {
    private EventIdFactory eventIdFactory;
    private EventChannel eventChannel;

    public ApartmentEventsPublisher(EventIdFactory eventIdFactory, EventChannel eventChannel) {
        this.eventIdFactory = eventIdFactory;
        this.eventChannel = eventChannel;
    }

    public void publishApartmentBooked(String id, String ownerId, String tenantId, Period period) {
        ApartmentBooked apartmentBooked = ApartmentBooked.create(eventIdFactory.create(), id, ownerId, tenantId, period);
        eventChannel.publish(apartmentBooked);
    }
}
