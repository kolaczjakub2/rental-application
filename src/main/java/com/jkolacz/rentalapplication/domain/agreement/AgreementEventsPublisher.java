package com.jkolacz.rentalapplication.domain.agreement;

import com.jkolacz.rentalapplication.domain.booking.RentalType;
import com.jkolacz.rentalapplication.domain.clock.Clock;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import com.jkolacz.rentalapplication.domain.money.Money;

import java.time.LocalDate;
import java.util.List;

public class AgreementEventsPublisher {
    private final EventChannel eventChannel;
    private final EventIdFactory eventIdFactory;
    private final Clock clock;

    public AgreementEventsPublisher(EventChannel eventChannel, EventIdFactory eventIdFactory, Clock clock) {
        this.eventChannel = eventChannel;
        this.eventIdFactory = eventIdFactory;
        this.clock = clock;
    }

    @SuppressWarnings("checkstyle:ParameterNumber")
    void agreementAccepted(RentalType rentalType, String rentalPlaceId, String ownerId, String tenantId, List<LocalDate> days, Money price) {
        AgreementAccepted event = new AgreementAccepted(
                eventIdFactory.create(), clock.now(), rentalType.name(), rentalPlaceId, ownerId, tenantId, days, price.getValue());
        eventChannel.publish(event);
    }
}
