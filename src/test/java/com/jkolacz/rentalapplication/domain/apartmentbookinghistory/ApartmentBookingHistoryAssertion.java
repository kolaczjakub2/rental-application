package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.function.Consumer;

public class ApartmentBookingHistoryAssertion {
    private ApartmentBookingHistory actual;

    public ApartmentBookingHistoryAssertion(ApartmentBookingHistory actual) {
        this.actual = actual;
    }

    public static ApartmentBookingHistoryAssertion assertThat(ApartmentBookingHistory actual) {
        return new ApartmentBookingHistoryAssertion(actual);
    }


    public ApartmentBookingHistoryAssertion hasApartmentIdEqualsTo(String apartmentId) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("apartmentId", apartmentId);
        return this;
    }


    public ApartmentBookingHistoryAssertion hasApartmentBookingSatisfies(Consumer<ApartmentBooking> consumer) {
        hasApartmentBookings().satisfies(actualBookings -> {
            Assertions.assertThat(asApartmentBookingList(actualBookings)).anySatisfy(consumer);
        });
        return this;
    }

    private AbstractObjectAssert<?, ?> hasApartmentBookings() {
        return Assertions.assertThat(actual).extracting("bookings");
    }

    private List<ApartmentBooking> asApartmentBookingList(Object bookings) {
        return (List<ApartmentBooking>) bookings;
    }

    public ApartmentBookingHistoryAssertion hasApartmentBookingsAmount(int size) {
        hasApartmentBookings().satisfies(bookings ->
                Assertions.assertThat(asApartmentBookingList(bookings)).hasSize(size));
        return this;
    }
}
