package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ApartmentBookingAssertion {
    private final ApartmentBooking actual;

    public ApartmentBookingAssertion(ApartmentBooking actual) {
        this.actual = actual;
    }

    public static ApartmentBookingAssertion assertThat(ApartmentBooking actual) {
        return new ApartmentBookingAssertion(actual);
    }

    ApartmentBookingAssertion hasBookingDateTimeEqualTo(LocalDateTime expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingDateTime", expected);
        return this;
    }

    ApartmentBookingAssertion hasOwnerIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("ownerId", expected);

        return this;
    }

    ApartmentBookingAssertion hasTenantIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("tenantId", expected);
        return this;
    }

    ApartmentBookingAssertion hasBookingPeriodThatHas(LocalDate expectedStart, LocalDate expectedEnd) {
        Assertions.assertThat(actual)
                .hasFieldOrPropertyWithValue("bookingPeriod.periodStart", expectedStart)
                .hasFieldOrPropertyWithValue("bookingPeriod.periodEnd", expectedEnd);
        return this;
    }

    ApartmentBookingAssertion isStart() {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("step", BookingStep.START);
        return this;
    }
}
