package com.jkolacz.rentalapplication.application.booking;

import com.jkolacz.rentalapplication.domain.apartment.BookingAccepted;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class BookingAcceptedAssertions {

    private BookingAccepted actual;

    public BookingAcceptedAssertions(BookingAccepted actual) {
        this.actual = actual;
    }

    public static BookingAcceptedAssertions assertThat(BookingAccepted actual) {
        return new BookingAcceptedAssertions(actual);
    }

    public BookingAcceptedAssertions hasRentalTypeEqualsTo(String rentalType) {
        Assertions.assertThat(actual.getRentalType()).isEqualTo(rentalType);
        return this;
    }

    public BookingAcceptedAssertions hasRentalPlaceIdEqualsTo(String rentalPlaceId) {
        Assertions.assertThat(actual.getRentalPlaceId()).isEqualTo(rentalPlaceId);
        return this;
    }

    public BookingAcceptedAssertions hasTenantIdEqualsTo(String tenantId) {
        Assertions.assertThat(actual.getTenantId()).isEqualTo(tenantId);
        return this;
    }

    public BookingAcceptedAssertions hasDaysContainsExactlyElementsOf(List<LocalDate> days) {
        Assertions.assertThat(actual.getDays()).containsExactlyElementsOf(days);
        return this;
    }
}
