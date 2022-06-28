package com.jkolacz.rentalapplication.application.hotel;

import com.jkolacz.rentalapplication.domain.booking.BookingRepository;
import com.jkolacz.rentalapplication.domain.clock.Clock;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import com.jkolacz.rentalapplication.domain.hotel.HotelEventsPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HotelApplicationServiceFactory {
    @Bean
    @SuppressWarnings("checkstyle:ParameterNumber")
    HotelApplicationService hotelApplicationService(
            HotelRepository hotelRepository, BookingRepository bookingRepository, EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        HotelEventsPublisher hotelEventsPublisher = new HotelEventsPublisher(eventIdFactory, clock, eventChannel);

        return new HotelApplicationService(hotelRepository, bookingRepository, hotelEventsPublisher);
    }
}
