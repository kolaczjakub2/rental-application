package com.jkolacz.payment.domain.events;

import com.jkolacz.payment.domain.payment.PaymentCompleted;
import com.jkolacz.payment.domain.payment.PaymentFailed;

public interface PaymentEventChannel {
    void publish(PaymentCompleted paymentCompleted);

    void publish(PaymentFailed paymentFailed);
}
