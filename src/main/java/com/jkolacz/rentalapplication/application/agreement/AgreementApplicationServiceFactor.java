package com.jkolacz.rentalapplication.application.agreement;

import com.jkolacz.rentalapplication.domain.agreement.AgreementEventsPublisher;
import com.jkolacz.rentalapplication.domain.agreement.AgreementRepository;
import com.jkolacz.rentalapplication.domain.clock.Clock;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;

public class AgreementApplicationServiceFactor {
    AgreementApplicationService agreementApplicationService(
            AgreementRepository agreementRepository, EventChannel eventChannel, EventIdFactory eventIdFactory, Clock clock) {
        return new AgreementApplicationService(agreementRepository, new AgreementEventsPublisher(eventChannel, eventIdFactory, clock));
    }
}
