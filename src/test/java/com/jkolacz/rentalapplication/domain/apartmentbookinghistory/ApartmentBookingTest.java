package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

import com.jkolacz.rentalapplication.domain.period.Period;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingAssertion.assertThat;

class ApartmentBookingTest {

    @Test
    void shouldCreateStartApartmentBookingWithAllRequiredInformation() {
        LocalDateTime bookingDateTime = LocalDateTime.of(2020, 1, 2, 3, 4);
        String ownerId = "123";
        String tenantId = "234";
        LocalDate start = LocalDate.of(2020, 2, 1);
        LocalDate end = LocalDate.of(2020, 2, 8);

        ApartmentBooking actual = ApartmentBooking.start(bookingDateTime, ownerId, tenantId, new Period(start, end));

        assertThat(actual)
                .isStart()
                .hasBookingDateTimeEqualTo(bookingDateTime)
                .hasOwnerIdEqualTo(ownerId)
                .hasTenantIdEqualTo(tenantId)
                .hasPeriodThatHas(start, end);
    }
}