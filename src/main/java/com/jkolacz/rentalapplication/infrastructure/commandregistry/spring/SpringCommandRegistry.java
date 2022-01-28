package com.jkolacz.rentalapplication.infrastructure.commandregistry.spring;

import com.jkolacz.rentalapplication.application.booking.BookingAccept;
import com.jkolacz.rentalapplication.application.booking.BookingReject;
import com.jkolacz.rentalapplication.application.commandregistry.CommandRegistry;
import org.springframework.context.ApplicationEventPublisher;

public class SpringCommandRegistry implements CommandRegistry {

    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringCommandRegistry(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void register(BookingReject bookingReject) {
        applicationEventPublisher.publishEvent(bookingReject);
    }

    @Override
    public void register(BookingAccept bookingAccept) {
        applicationEventPublisher.publishEvent(bookingAccept);
    }
}
