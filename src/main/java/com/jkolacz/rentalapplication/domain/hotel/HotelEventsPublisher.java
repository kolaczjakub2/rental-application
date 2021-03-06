package com.jkolacz.rentalapplication.domain.hotel;

import com.jkolacz.rentalapplication.domain.clock.Clock;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

import java.time.LocalDate;
import java.util.List;

public class HotelEventsPublisher {
    private final EventIdFactory eventIdFactory;
    private final Clock clock;
    private final EventChannel eventChannel;

    public HotelEventsPublisher(EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        this.eventIdFactory = eventIdFactory;
        this.clock = clock;
        this.eventChannel = eventChannel;
    }

    void publishHotelRoomBooked(String hotelId, int hotelRoomNumber, String tenantId, List<LocalDate> days) {
        HotelRoomBooked hotelRoomBooked = new HotelRoomBooked(eventIdFactory.create(), clock.now(), hotelId, hotelRoomNumber, tenantId, days);
        eventChannel.publish(hotelRoomBooked);
    }
}
