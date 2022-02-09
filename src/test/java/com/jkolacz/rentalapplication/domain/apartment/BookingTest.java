package com.jkolacz.rentalapplication.domain.apartment;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

class BookingTest {

    @Test
    public void shouldCreateApartmentBooking() {

        String rentalPlaceId = "1234";
        String tenantId = "7890";
        LocalDate start = LocalDate.of(2022, 1, 1);
        LocalDate end = LocalDate.of(2022, 1, 3);
        Booking actual = Booking.apartment(rentalPlaceId, tenantId, new Period(start, end));

        BookingAssertion.assertThat(actual)
                .isOpen()
                .isApartment()
                .hasRentalPlaceIdEqualsTo(rentalPlaceId)
                .hasTenantIdEqualsTo(tenantId)
                .containsAllDays(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 2), LocalDate.of(2022, 1, 3));
    }


    @Test
    public void shouldCreateHotelRoomBooking() {

        String rentalPlaceId = "1234";
        String tenantId = "7890";
        LocalDate start = LocalDate.of(2022, 1, 1);
        LocalDate end = LocalDate.of(2022, 1, 3);
        List<LocalDate> days = asList(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 2), LocalDate.of(2022, 1, 3));

        Booking actual = Booking.hotelRoom(rentalPlaceId, tenantId, days);


        BookingAssertion.assertThat(actual)
                .isOpen()
                .isHotelRoom()
                .hasRentalPlaceIdEqualsTo(rentalPlaceId)
                .hasTenantIdEqualsTo(tenantId)
                .containsAllDays(days);
    }

}