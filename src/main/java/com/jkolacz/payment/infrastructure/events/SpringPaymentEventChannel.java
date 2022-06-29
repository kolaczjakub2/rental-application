package com.jkolacz.payment.infrastructure.events;

import com.jkolacz.payment.domain.events.PaymentEventChannel;
import com.jkolacz.payment.domain.payment.PaymentCompleted;
import com.jkolacz.payment.domain.payment.PaymentFailed;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
class SpringPaymentEventChannel implements PaymentEventChannel {
    private final ApplicationEventPublisher publisher;

    SpringPaymentEventChannel(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(PaymentCompleted paymentCompleted) {
        publisher.publishEvent(paymentCompleted);
    }

    @Override
    public void publish(PaymentFailed paymentFailed) {
        publisher.publishEvent(paymentFailed);
    }
}
