package com.jkolacz.rentalapplication.domain.agreement;

import com.jkolacz.rentalapplication.domain.booking.RentalType;
import com.jkolacz.rentalapplication.domain.money.Money;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.util.List;

import static com.jkolacz.rentalapplication.domain.agreement.Agreement.Builder.agreement;

public class AgreementAssertion {
    private final Agreement actual;

    private AgreementAssertion(Agreement actual) {
        this.actual = actual;
    }

    public static AgreementAssertion assertThat(Agreement actual) {
        return new AgreementAssertion(actual);
    }

    public AgreementAssertion isEqualToAgreement(RentalType rentalType, String rentalPlaceId, String ownerId, String tenantId, List<LocalDate> days, Money price) {
        Agreement expected = agreement()
                .withRentalType(rentalType)
                .withRentalPlaceId(rentalPlaceId)
                .withOwnerId(ownerId)
                .withTenantId(tenantId)
                .withDays(days)
                .withPrice(price)
                .build();
        Assertions.assertThat(actual).isEqualTo(expected);
        return this;
    }
}
