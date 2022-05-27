package com.jkolacz.rentalapplication.domain.apartment;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ApartmentBookedTest {


    public static final String APARTMENT_ID = "1234";
    public static final String OWNER_ID = "5678";
    public static final String TENANT_ID = "3456";
    public static final LocalDate PERIOD_START = LocalDate.of(2020, 1, 1);
    public static final LocalDate PERIOD_END = LocalDate.of(2020, 2, 1);

    @Test
    void shouldCreateEventWithAllInformation() {
        Period period = new Period(PERIOD_START, PERIOD_END);
        LocalDateTime afterNow = LocalDateTime.now().plusSeconds(1);
        LocalDateTime beforeNow = LocalDateTime.now().minusSeconds(1);

        String eventId = "87543";
        ApartmentBooked actual = ApartmentBooked.create(eventId,APARTMENT_ID, OWNER_ID, TENANT_ID, period);


        assertThat(actual.getEventId()).matches(eventId);

        assertThat(actual.getEventCreationDateTime())
                .isBefore(afterNow)
                .isAfter(beforeNow);
        assertThat(actual.getApartmentId()).isEqualTo(APARTMENT_ID);
        assertThat(actual.getOwnerId()).isEqualTo(OWNER_ID);
        assertThat(actual.getTenantId()).isEqualTo(TENANT_ID);
        assertThat(actual.getPeriodStart()).isEqualTo(PERIOD_START);
        assertThat(actual.getPeriodEnd()).isEqualTo(PERIOD_END);
    }
}