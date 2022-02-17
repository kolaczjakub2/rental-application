package com.jkolacz.rentalapplication.application.apartmentbookinghistory;

import com.jkolacz.rentalapplication.domain.apartment.ApartmentBooked;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentBookedTestFactory;
import com.jkolacz.rentalapplication.domain.apartment.Period;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ApartmentBookingHistoryEventListenerTest {

    private static final String APARTMENT_ID = "1234";
    private static final String OWNER_ID = "5678";
    private static final String TENANT_ID = "8989";
    private static final LocalDate START = LocalDate.of(2020, 10, 11);
    private static final LocalDate END = LocalDate.of(2020, 10, 12);
    private static final Period PERIOD = new Period(START, END);
    private static final int FIRST_SIZE = 1;
    private static final int TWO_BOOKINGS = 2;

    private final ArgumentCaptor<ApartmentBookingHistory> captor = ArgumentCaptor.forClass(ApartmentBookingHistory.class);
    private final ApartmentBookingHistoryRepository repository = mock(ApartmentBookingHistoryRepository.class);
    private final ApartmentBookingHistoryEventListener eventListener = new ApartmentBookingHistoryEventListener(repository);

    @Test
    void shouldCreateApartmentBookingHistoryWhenConsumingApartmentBooked() {
        givenNotExistingApartmentBookingHistory();

        eventListener.consume(givenApartmentBooked());

        BDDMockito.then(repository).should().save(captor.capture());
        thenApartmentBookingHistoryShouldHaveApartmentBookings(captor.getValue(), FIRST_SIZE);
    }

    @Test
    void shouldUpdateExistingApartmentBookingHistoryWhenConsumingApartmentBooked() {
        givenExistingApartmentBookingHistory();

        eventListener.consume(givenApartmentBooked());

        BDDMockito.then(repository).should().save(captor.capture());
        thenApartmentBookingHistoryShouldHaveApartmentBookings(captor.getValue(), TWO_BOOKINGS);
    }

    private void givenExistingApartmentBookingHistory() {
        BDDMockito.given(repository.existFor(APARTMENT_ID)).willReturn(Boolean.TRUE);
        ApartmentBookingHistory apartmentBookingHistory = new ApartmentBookingHistory(APARTMENT_ID);
        apartmentBookingHistory.add(ApartmentBooking.start(LocalDateTime.now(), OWNER_ID, "9807", new BookingPeriod(LocalDate.now(), LocalDate.now().plusDays(1))));
        BDDMockito.given(repository.findFor(APARTMENT_ID)).willReturn(apartmentBookingHistory);

    }

    private void givenNotExistingApartmentBookingHistory() {
        BDDMockito.given(repository.existFor(APARTMENT_ID)).willReturn(Boolean.FALSE);
    }


    private void thenApartmentBookingHistoryShouldHaveApartmentBookings(ApartmentBookingHistory actual, int bookingsSize) {
        assertThat(actual).extracting("bookings").satisfies(actualBookings -> {
            List<ApartmentBooking> bookings = (List<ApartmentBooking>) actualBookings;
            assertThat(bookings)
                    .hasSize(bookingsSize)
                    .anySatisfy(actualBooking -> {
                        ApartmentBookingAssertion.assertThat(actualBooking)
                                .hasOwnerIdEqualTo(OWNER_ID)
                                .hasTenantIdEqualTo(TENANT_ID)
                                .hasBookingPeriodThatHas(START, END);

                    });

        });

    }


    private ApartmentBooked givenApartmentBooked() {
        return ApartmentBookedTestFactory.create(APARTMENT_ID, OWNER_ID, TENANT_ID, PERIOD);
    }
}