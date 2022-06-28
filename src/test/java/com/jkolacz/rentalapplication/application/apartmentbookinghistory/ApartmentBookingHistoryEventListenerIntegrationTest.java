package com.jkolacz.rentalapplication.application.apartmentbookinghistory;

import com.google.common.collect.ImmutableMap;
import com.jkolacz.rentalapplication.application.apartment.ApartmentApplicationService;
import com.jkolacz.rentalapplication.application.apartment.ApartmentBookingDto;
import com.jkolacz.rentalapplication.domain.apartment.Apartment;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingAssertion;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistoryAssertion;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartment.SpringJpaApartmentTestRepository;
import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory.SpringJpaApartmentBookingHistoryTestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
    private static final Map<String, Double> SPACES_DEFINITION = ImmutableMap.of("Toilet", 10.0, "Bedroom", 30.0);

    @Autowired private ApartmentApplicationService apartmentApplicationService;
    @Autowired private ApartmentBookingHistoryRepository apartmentBookingHistoryRepository;
    @Autowired private SpringJpaApartmentBookingHistoryTestRepository springJpaApartmentBookingHistoryTestRepository;
    @Autowired private ApartmentRepository apartmentRepository;
    @Autowired private SpringJpaApartmentTestRepository springJpaApartmentTestRepository;

    private String apartmentId;

    @AfterEach
    void removeApartment() {
        springJpaApartmentTestRepository.deleteById(apartmentId);
        springJpaApartmentBookingHistoryTestRepository.deleteById(apartmentId);
    }

    @Test
    @Transactional
    void shouldUpdateApartmentBookingHistory() {
        String tenantId = "11223344";
        LocalDate start = LocalDate.of(2020, 1, 13);
        LocalDate end = LocalDate.of(2020, 1, 14);
        givenExistingApartment();
        ApartmentBookingDto apartmentBookingDto = new ApartmentBookingDto(apartmentId, tenantId, start, end);


        apartmentApplicationService.book(apartmentBookingDto);
        ApartmentBookingHistory actual = apartmentBookingHistoryRepository.findFor(apartmentId);

        ApartmentBookingHistoryAssertion.assertThat(actual)
                .hasOneApartmentBooking()
                .hasApartmentBookingThatSatisfies(actualBooking -> {
                    ApartmentBookingAssertion.assertThat(actualBooking)
                            .hasOwnerIdEqualTo(OWNER_ID)
                            .hasTenantIdEqualTo(tenantId)
                            .hasPeriodThatHas(start, end);
                });
    }

    private void givenExistingApartment() {
        apartmentId = apartmentRepository.save(createApartment());
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
                .withSpacesDefinition(SPACES_DEFINITION)
                .build();
    }
}