package com.jkolacz.rentalapplication.application.apartmentbookinghistory;

import com.google.common.collect.ImmutableMap;
import com.jkolacz.rentalapplication.application.apartment.ApartmentApplicationService;
import com.jkolacz.rentalapplication.domain.apartment.Apartment;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBooking;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingAssertion;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.jkolacz.rentalapplication.domain.apartment.Apartment.Builder.apartment;

@SpringBootTest
@Tag("IntegrationTest")
class ApartmentBookingHistoryEventListenerIntegrationTest {
    private static final String OWNER_ID = "1234";
    private static final String STREET = "Florianska";
    private static final String POSTAL_CODE = "12-345";
    private static final String HOUSE_NUMBER = "1";
    private static final String APARTMENT_NUMBER = "13";
    private static final String CITY = "Cracow";
    private static final String COUNTRY = "Poland";
    private static final String DESCRIPTION = "Nice place to stay";
    private static final Map<String, Double> ROOMS_DEFINITION = ImmutableMap.of("Toilet", 10.0, "Bedroom", 30.0);

    @Autowired
    private ApartmentApplicationService apartmentApplicationService;

    @Autowired
    private ApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Test
    @Transactional
    void shouldUpdateApartmentBookingHistory() {
        String tenantId = "11223344";
        LocalDate start = LocalDate.of(2020, 1, 13);
        LocalDate end = LocalDate.of(2020, 1, 14);
        String apartmentId = givenExistingApartment();

        apartmentApplicationService.book(apartmentId, tenantId, start, end);
        ApartmentBookingHistory actual = apartmentBookingHistoryRepository.findFor(apartmentId);

        Assertions.assertThat(actual).extracting("bookings").satisfies(actualBookings -> {
            List<ApartmentBooking> bookings = (List<ApartmentBooking>) actualBookings;

            Assertions.assertThat(bookings).hasSize(1)
                    .allSatisfy(booking -> {
                        ApartmentBookingAssertion.assertThat(booking)
                                .isStart()
                                .hasOwnerIdEqualTo(OWNER_ID)
                                .hasTenantIdEqualTo(tenantId)
                                .hasBookingPeriodThatHas(start, end);
                    });
        });
    }

    private String givenExistingApartment() {
        return apartmentRepository.save(createApartment());
    }

    private Apartment createApartment() {
        return apartment()
                .withOwnerId(OWNER_ID)
                .withStreet(STREET)
                .withPostalCode(POSTAL_CODE)
                .withHouseNumber(HOUSE_NUMBER)
                .withApartmentNumber(APARTMENT_NUMBER)
                .withCity(CITY)
                .withCountry(COUNTRY)
                .withDescription(DESCRIPTION)
                .withRoomsDefinition(ROOMS_DEFINITION)
                .build();
    }
}