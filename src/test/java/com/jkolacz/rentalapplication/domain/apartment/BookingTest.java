package com.jkolacz.rentalapplication.domain.apartment;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;

class BookingTest {

    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));
    private static final String TENANT_ID = "1234";
    private static final String RENTAL_PLACE_ID = "5748";

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

    @Test
    void shouldChangeStatusOfBookingOnceRejected() {
        Booking booking =Booking.hotelRoom(RENTAL_PLACE_ID,TENANT_ID,DAYS);

        booking.reject();

        BookingAssertion.assertThat(booking).isRejected();
    }
}