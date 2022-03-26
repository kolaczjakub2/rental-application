package com.jkolacz.rentalapplication.domain.hotelBookingHistory;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

public class HotelRoomBookingHistoryAssertion {
    private HotelRoomBookingHistory actual;

    public HotelRoomBookingHistoryAssertion(HotelRoomBookingHistory hotelRoomBookingHistory) {
        actual = hotelRoomBookingHistory;
    }

    public static HotelRoomBookingHistoryAssertion assertThat(HotelRoomBookingHistory hotelRoomBookingHistory) {
        return new HotelRoomBookingHistoryAssertion(hotelRoomBookingHistory);
    }

    public HotelRoomBookingHistoryAssertion hasHotelRoomIdEqualTo(String hotelRoomId) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("hotelRoomId", hotelRoomId);
        return this;

    }

    public HotelRoomBookingHistoryAssertion hasInformationAboutBookings(int size) {
        Assertions.assertThat(actual).extracting("bookings")
                .satisfies(bookings -> Assertions.assertThat(asHotelRoomBooking(bookings)).hasSize(size));
        return this;
    }


    public HotelRoomBookingHistoryAssertion hasHotelRoomBookingFor(LocalDateTime bookingDateTime, String tenantId, List<LocalDate> days) {
        return hasHotelRoomBookingFor(hotelRoomBooking -> {
            Assertions.assertThat(hotelRoomBooking)
                    .hasFieldOrPropertyWithValue("bookingDateTime", bookingDateTime)
                    .hasFieldOrPropertyWithValue("tenantId", tenantId)
                    .hasFieldOrPropertyWithValue("days", days);
        });
    }

    public HotelRoomBookingHistoryAssertion hasHotelRoomBookingFor(String tenantId, List<LocalDate> days) {
        return hasHotelRoomBookingFor(hotelRoomBooking -> {
            Assertions.assertThat(hotelRoomBooking)
                    .hasFieldOrPropertyWithValue("tenantId", tenantId)
                    .hasFieldOrPropertyWithValue("days", days);
        });
    }

    private HotelRoomBookingHistoryAssertion hasHotelRoomBookingFor(Consumer<HotelRoomBooking> consumer) {
        hasHotelRoomBookings().satisfies(bookings -> {
            Assertions.assertThat(asHotelRoomBooking(bookings)).anySatisfy(consumer);
        });

        return this;
    }

    private List<HotelRoomBooking> asHotelRoomBooking(Object bookings) {
        return (List<HotelRoomBooking>) bookings;
    }

    private AbstractObjectAssert<?, ?> hasHotelRoomBookings() {
        return Assertions.assertThat(actual).extracting("bookings");
    }


}
