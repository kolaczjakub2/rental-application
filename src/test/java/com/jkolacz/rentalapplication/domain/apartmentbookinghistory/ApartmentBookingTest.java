package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


class ApartmentBookingTest {

    @Test
    public void shouldCreateApartmentBookingWithAllRequiredInformation() {
        LocalDateTime bookingDateTime = LocalDateTime.now();
        String ownerId = "1234";
        String tenantId = "7890";
        LocalDate start = LocalDate.of(2022, 1, 1);
        LocalDate end = LocalDate.of(2022, 1, 3);


        ApartmentBooking actual = ApartmentBooking.start(bookingDateTime, ownerId, tenantId, new BookingPeriod(start, end));
        ApartmentBookingAssertion.assertThat(actual)
                .isStart()
                .hasBookingDateTimeEqualTo(bookingDateTime)
                .hasOwnerIdEqualTo(ownerId)
                .hasTenantIdEqualTo(tenantId)
                .hasBookingPeriodThatHas(start, end);

    }


}