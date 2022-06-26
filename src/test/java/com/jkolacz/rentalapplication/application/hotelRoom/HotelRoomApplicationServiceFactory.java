package com.jkolacz.rentalapplication.application.hotelroom;

import com.jkolacz.rentalapplication.application.hotel.HotelRoomApplicationService;
import com.jkolacz.rentalapplication.domain.booking.BookingRepository;
import com.jkolacz.rentalapplication.domain.clock.Clock;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomEventsPublisher;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HotelRoomApplicationServiceFactory {
    @Bean
    HotelRoomApplicationService hotelRoomApplicationService(HotelRepository hotelRepository,
                                                            HotelRoomRepository hotelRoomRepository, BookingRepository bookingRepository, EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        HotelRoomEventsPublisher hotelRoomEventsPublisher = new HotelRoomEventsPublisher(eventIdFactory, clock, eventChannel);

        return new HotelRoomApplicationService(hotelRepository, hotelRoomRepository, bookingRepository, hotelRoomEventsPublisher);
    }
}
