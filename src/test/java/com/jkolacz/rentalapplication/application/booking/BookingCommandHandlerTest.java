package com.jkolacz.rentalapplication.application.booking;

import com.jkolacz.rentalapplication.domain.apartment.Booking;
import com.jkolacz.rentalapplication.domain.apartment.BookingAccepted;
import com.jkolacz.rentalapplication.domain.apartment.BookingAssertion;
import com.jkolacz.rentalapplication.domain.apartment.BookingRepository;
import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class BookingCommandHandlerTest {

    private static final String BOOKING_ID = "74398";
    private static final String RENTAL_PLACE_ID = "1234";
    private static final String TENANT_ID = "5678";
    private static final List<LocalDate> DAYS = asList(LocalDate.now(), LocalDate.now().plusDays(1));

    private final BookingRepository bookingRepository = mock(BookingRepository.class);
    private final EventChannel eventChannel = mock(EventChannel.class);
    BookingCommandHandler commandHandler = new BookingCommandHandler(bookingRepository, eventChannel);

    ArgumentCaptor<Booking> captor = ArgumentCaptor.forClass(Booking.class);

    @Test
    void shouldAcceptBooking() {
        givenOpenBooking();

        commandHandler.accept(new BookingAccept(BOOKING_ID));
        then(bookingRepository).should().save(captor.capture());
        Booking actual = captor.getValue();
        BookingAssertion.assertThat(actual)
                .isAccepted();
    }


    @Test
    void shouldPublishBookingAcceptedOnceAccepted() {
        ArgumentCaptor<BookingAccepted> captor = ArgumentCaptor.forClass(BookingAccepted.class);
        givenOpenBooking();

        commandHandler.accept(new BookingAccept(BOOKING_ID));
        BDDMockito.then(eventChannel).should().publish(captor.capture());
        BookingAccepted actual = captor.getValue();

        BookingAcceptedAssertions.assertThat(actual)
                .hasRentalTypeEqualsTo("HOTEL_ROOM")
                .hasRentalPlaceIdEqualsTo(RENTAL_PLACE_ID)
                .hasTenantIdEqualsTo(TENANT_ID)
                .hasDaysContainsExactlyElementsOf(DAYS);

    }

    @Test
    void shouldBookingReject() {
        givenOpenBooking();

        commandHandler.reject(new BookingReject(BOOKING_ID));
        then(bookingRepository).should().save(captor.capture());
        Booking actual = captor.getValue();
        BookingAssertion.assertThat(actual)
                .isRejected();
    }


    private void givenOpenBooking() {
        Booking booking = Booking.hotelRoom(RENTAL_PLACE_ID, TENANT_ID, DAYS);
        given(bookingRepository.findById(BOOKING_ID)).willReturn(booking);
    }
}