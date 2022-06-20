package com.jkolacz.rentalapplication.application.apartment;

import com.jkolacz.rentalapplication.application.apartment.ApartmentApplicationService;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentEventsPublisher;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import com.jkolacz.rentalapplication.domain.booking.BookingRepository;
import com.jkolacz.rentalapplication.domain.clock.Clock;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ApartmentApplicationServiceFactory {
    @Bean
    ApartmentApplicationService apartmentApplicationService(
            ApartmentRepository apartmentRepository, BookingRepository bookingRepository, EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        ApartmentEventsPublisher apartmentEventsPublisher = new ApartmentEventsPublisher(eventIdFactory, clock, eventChannel);

        return new ApartmentApplicationService(apartmentRepository, bookingRepository, apartmentEventsPublisher);
    }
}
