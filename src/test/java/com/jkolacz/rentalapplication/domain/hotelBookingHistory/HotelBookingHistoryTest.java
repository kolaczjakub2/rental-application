package com.jkolacz.rentalapplication.domain.hotelBookingHistory;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

class HotelBookingHistoryTest {

    private static final String HOTEL_ID = "hotelId1";
    private static final String HOTEL_ROOM_ID = "hotelRoomId1";
    private static final LocalDateTime BOOKING_DATE_TIME = LocalDateTime.of(2020, 1, 2, 3, 4);
    private static final String TENANT_ID = "tenantId1";
    private static final List<LocalDate> DAYS = asList(LocalDate.of(2020, 5, 6), LocalDate.of(2020, 7, 8));

    @Test
    public void shouldAddFirstHotelRoomBookingHistoryForHotelRoom() {
        HotelBookingHistory hotelBookingHistory = new HotelBookingHistory(HOTEL_ID);

        hotelBookingHistory.add(HOTEL_ROOM_ID, BOOKING_DATE_TIME, TENANT_ID, DAYS);

        HotelBookingHistoryAssertion.assertThat(hotelBookingHistory)
                .hasInformationAboutHistoryOfHotelRooms(1)
                .hasInformationAboutHistoryOfHotelRoom(HOTEL_ROOM_ID, 1)
                .hasHotelRoomBookingHistoryFor(HOTEL_ROOM_ID, BOOKING_DATE_TIME, TENANT_ID, DAYS);
    }

}