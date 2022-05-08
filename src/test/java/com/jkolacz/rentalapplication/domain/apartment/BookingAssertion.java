package com.jkolacz.rentalapplication.domain.apartment;

import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

public class BookingAssertion {
    private final Booking actual;

    public BookingAssertion(Booking actual) {
        this.actual = actual;
    }

    public static  BookingAssertion assertThat(Booking actual) {
        return new  BookingAssertion(actual);
    }


    public BookingAssertion hasRentalPlaceIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalPlaceId", expected);
        return this;
    }

    public BookingAssertion hasTenantIdEqualTo(String expected) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("tenantId", expected);
        return this;
    }

    public BookingAssertion isOpen() {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingStatus", BookingStatus.OPEN);
        return this;
    }

    public BookingAssertion isApartment() {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalType", RentalType.APARTMENT);
        return this;
    }

    public BookingAssertion isHotelRoom() {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("rentalType", RentalType.HOTEL_ROOM);
        return this;
    }

    public BookingAssertion containsAllDays(LocalDate... expected) {
        return containsAllDays(asList(expected));
    }

    public BookingAssertion containsAllDays(List<LocalDate> expected) {
        Assertions.assertThat(actual).extracting("days").satisfies(days->{
            List<LocalDate> actualDays = (List<LocalDate>) days;
            Assertions.assertThat(actualDays).containsExactlyElementsOf(expected);
        });
        return this;
    }


    public BookingAssertion isRejected() {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingStatus", BookingStatus.REJECTED);
        return this;
    }

    public BookingAssertion isAccepted() {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("bookingStatus", BookingStatus.ACCEPTED);
        return this;
    }
}
