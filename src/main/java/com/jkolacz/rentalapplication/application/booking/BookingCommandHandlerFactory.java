package com.jkolacz.rentalapplication.application.booking;

import com.jkolacz.rentalapplication.domain.booking.BookingDomainService;
import com.jkolacz.rentalapplication.domain.booking.BookingDomainServiceFactory;
import com.jkolacz.rentalapplication.domain.booking.BookingEventsPublisher;
import com.jkolacz.rentalapplication.domain.booking.BookingRepository;
import com.jkolacz.rentalapplication.domain.clock.Clock;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BookingCommandHandlerFactory {
    @Bean
    BookingCommandHandler bookingCommandHandler(
            BookingRepository bookingRepository, EventIdFactory eventIdFactory, Clock clock, EventChannel eventChannel) {
        BookingDomainService bookingDomainService = new BookingDomainServiceFactory().create(eventIdFactory, clock, eventChannel);
        return new BookingCommandHandler(bookingRepository, bookingDomainService, new BookingEventsPublisher(eventIdFactory, clock, eventChannel));
    }
}
