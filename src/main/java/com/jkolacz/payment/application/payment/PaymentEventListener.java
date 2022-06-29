package com.jkolacz.payment.application.payment;

import com.jkolacz.payment.domain.payment.Payment;
import com.jkolacz.payment.domain.payment.PaymentFactory;
import com.jkolacz.rentalapplication.domain.agreement.AgreementAccepted;
import org.springframework.context.event.EventListener;

public class PaymentEventListener {
    private final PaymentFactory paymentFactory;

    PaymentEventListener(PaymentFactory paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

    @EventListener
    void consume(AgreementAccepted agreementAccepted) {
        Payment payment = paymentFactory
                .create(agreementAccepted.getTenantId(), agreementAccepted.getOwnerId(), agreementAccepted.getDays(), agreementAccepted.getPrice());

        payment.pay();
    }
}
