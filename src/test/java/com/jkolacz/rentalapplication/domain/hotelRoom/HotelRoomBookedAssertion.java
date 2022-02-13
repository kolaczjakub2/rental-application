package com.jkolacz.rentalapplication.domain.hotelRoom;

import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

public class HotelRoomBookedAssertion {
    private HotelRoomBooked actual;

    public HotelRoomBookedAssertion(HotelRoomBooked actual) {
        this.actual = actual;
    }

    public static HotelRoomBookedAssertion assertThat(HotelRoomBooked actual) {
        return new HotelRoomBookedAssertion(actual);
    }

    public HotelRoomBookedAssertion hasEventIdMatchesPattern() {
        Assertions.assertThat(actual.getEventId()).matches(Pattern.compile("[0-9a-z\\-]{36}"));
        return this;
    }

    public HotelRoomBookedAssertion hasEventCreationDateBefore(LocalDateTime time) {
        Assertions.assertThat(actual.getEventCreationDateTime()).isBefore(time);
        return this;
    }

    public HotelRoomBookedAssertion hasEventCreationDateAfter(LocalDateTime time) {
        Assertions.assertThat(actual.getEventCreationDateTime()).isAfter(time);
        return this;
    }

    public HotelRoomBookedAssertion hasHotelRoomIdEqualsTo(String hotelRoomId) {
        Assertions.assertThat(actual.getHotelRoomId()).isEqualTo(hotelRoomId);
        return this;
    }

    public HotelRoomBookedAssertion hasHotelIdEqualsTo(String hotelId) {
        Assertions.assertThat(actual.getHotelId()).isEqualTo(hotelId);
        return this;
    }

    public HotelRoomBookedAssertion hasTenantIdEqualsTo(String tenantId) {
        Assertions.assertThat(actual.getTenantId()).isEqualTo(tenantId);
        return this;
    }

    public HotelRoomBookedAssertion containsExactlyElementsOf(List<LocalDate> days) {
        Assertions.assertThat(actual.getDays()).containsExactlyElementsOf(days);
        return this;
    }
}
