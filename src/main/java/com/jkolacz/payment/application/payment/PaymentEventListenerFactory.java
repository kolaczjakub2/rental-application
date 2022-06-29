package com.jkolacz.payment.application.payment;

import com.jkolacz.payment.domain.events.PaymentEventChannel;
import com.jkolacz.payment.domain.payment.PaymentEventsPublisher;
import com.jkolacz.payment.domain.payment.PaymentFactory;
import com.jkolacz.payment.domain.payment.PaymentService;
import com.jkolacz.rentalapplication.domain.clock.Clock;
import com.jkolacz.rentalapplication.domain.event.EventIdFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentEventListenerFactory {
    @Bean
    PaymentEventListener paymentEventListener(PaymentService paymentService, PaymentEventChannel eventChannel, EventIdFactory eventIdFactory, Clock clock) {
        PaymentEventsPublisher paymentEventsPublisher = new PaymentEventsPublisher(eventChannel, eventIdFactory, clock);
        PaymentFactory paymentFactory = new PaymentFactory(paymentService, paymentEventsPublisher);
        return new PaymentEventListener(paymentFactory);
    }
}
