package com.jkolacz.rentalapplication.application.apartment;

import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import com.jkolacz.rentalapplication.domain.apartment.BookingRepository;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.ApartmentEventsPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApartmentApplicationServiceFactory {

    @Bean
    public ApartmentApplicationService create(ApartmentRepository apartmentRepository, ApartmentEventsPublisher eventChannel, BookingRepository bookingRepository) {
        return new ApartmentApplicationService(apartmentRepository, bookingRepository, new com.jkolacz.rentalapplication.domain.apartment.ApartmentEventsPublisher(new EventIdFactory(), eventChannel));
    }
}
