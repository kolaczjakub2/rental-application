package com.jkolacz.rentalapplication.domain.hotelRoom;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

class HotelRoomBookedTest {

    public static final String HOTEL_ROOM_ID = "1234";
    public static final String HOTEL_ID = "5678";
    public static final String TENANT_ID = "5678";
    public static final List<LocalDate> DAYS = asList(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2), LocalDate.of(2020, 1, 3));

    @Test
    void shouldCreateEventWithAllInformation() {
        LocalDateTime beforeNow = LocalDateTime.now().minusNanos(1);
        HotelRoomBooked actual = HotelRoomBooked.create(HOTEL_ROOM_ID, HOTEL_ID,TENANT_ID,DAYS);

        HotelRoomBookedAssertion.assertThat(actual)
                .hasEventIdMatchesPattern()
                .hasEventCreationDateBefore(LocalDateTime.now().plusNanos(1))
                .hasEventCreationDateAfter(beforeNow)
                .hasHotelRoomIdEqualsTo(HOTEL_ROOM_ID)
                .hasHotelIdEqualsTo(HOTEL_ID)
                .hasTenantIdEqualsTo(TENANT_ID)
                .containsExactlyElementsOf(DAYS);
    }
}