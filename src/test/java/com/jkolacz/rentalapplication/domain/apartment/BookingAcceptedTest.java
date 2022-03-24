package com.jkolacz.rentalapplication.domain.apartment;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

import static com.jkolacz.rentalapplication.domain.apartment.RentalType.APARTMENT;
import static org.assertj.core.api.Assertions.assertThat;

class BookingAcceptedTest {

    @Test
     void shouldCreateBookingAcceptedWithAllRequiredInformation() {
        String rentalPlaceId = "1234";
        String tenantId = "7890";
        List<LocalDate> days = ImmutableList.of(
                LocalDate.of(2022, 10, 10),
                LocalDate.of(2022, 10, 11),
                LocalDate.of(2022, 10, 12)
        );
        BookingAccepted actual = BookingAccepted.create(APARTMENT, rentalPlaceId, tenantId, days);
        LocalDateTime afterNow = LocalDateTime.now().plusSeconds(1);
        LocalDateTime beforeNow = LocalDateTime.now().minusSeconds(1);

        assertThat(actual.getEventId()).matches(Pattern.compile("[0-9a-z\\-]{36}"));

        assertThat(actual.getEventCreationDateTime())
                .isBefore(afterNow)
                .isAfter(beforeNow);
        assertThat(actual.getRentalType()).isEqualTo("APARTMENT");
        assertThat(actual.getRentalPlaceId()).isEqualTo(rentalPlaceId);
        assertThat(actual.getTenantId()).isEqualTo(tenantId);
        assertThat(actual.getDays()).isEqualTo(days);
    }

}