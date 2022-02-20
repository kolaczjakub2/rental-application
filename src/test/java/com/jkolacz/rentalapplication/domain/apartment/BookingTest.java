package com.jkolacz.rentalapplication.domain.apartment;

import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import java.time.LocalDate;
import java.util.List;

import static com.jkolacz.rentalapplication.domain.apartment.BookingAssertion.assertThat;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;

class BookingTest {

    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));
    private static final String TENANT_ID = "1234";
    private static final String RENTAL_PLACE_ID = "5748";

    private final EventChannel eventChannel = mock(EventChannel.class);

    @Test
    public void shouldCreateApartmentBooking() {

        String rentalPlaceId = "1234";
        String tenantId = "7890";
        LocalDate start = LocalDate.of(2022, 1, 1);
        LocalDate end = LocalDate.of(2022, 1, 3);
        Booking actual = Booking.apartment(rentalPlaceId, tenantId, new Period(start, end));

        assertThat(actual)
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
        List<LocalDate> days = asList(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 2), LocalDate.of(2022, 1, 3));

        Booking actual = Booking.hotelRoom(rentalPlaceId, tenantId, days);


        assertThat(actual)
                .isOpen()
                .isHotelRoom()
                .hasRentalPlaceIdEqualsTo(rentalPlaceId)
                .hasTenantIdEqualsTo(tenantId)
                .containsAllDays(days);
    }

    @Test
    void shouldChangeStatusOfBookingOnceAccepted() {
        Booking booking = Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID, DAYS);

        booking.accept(eventChannel);

        assertThat(booking).isAccepted();
    }


    @Test
    void shouldPublishBookingAcceptedOnceAccepted() {
        ArgumentCaptor<BookingAccepted> captor = ArgumentCaptor.forClass(BookingAccepted.class);
        Booking booking = Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID, DAYS);

        booking.accept(eventChannel);
        BDDMockito.then(eventChannel).should().publish(captor.capture());
        BookingAccepted actual = captor.getValue();
        Assertions.assertThat(actual.getRentalType()).isEqualTo("HOTEL_ROOM");
        Assertions.assertThat(actual.getRentalPlaceId()).isEqualTo(RENTAL_PLACE_ID);
        Assertions.assertThat(actual.getTenantId()).isEqualTo(TENANT_ID);
        Assertions.assertThat(actual.getDays()).containsExactlyElementsOf(DAYS);
    }

    @Test
    void shouldChangeStatusOfBookingOnceRejected() {
        Booking booking =Booking.hotelRoom(RENTAL_PLACE_ID,TENANT_ID,DAYS);

        booking.reject();

        assertThat(booking).isRejected();
    }

}