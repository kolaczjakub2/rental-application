package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartment;

import com.google.common.collect.ImmutableMap;
import com.jkolacz.rentalapplication.domain.apartment.Apartment;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentAssertion;
import com.jkolacz.rentalapplication.domain.apartment.ApartmentRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.UUID;

import static com.jkolacz.rentalapplication.domain.apartment.Apartment.Builder.apartment;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaApartmentRepositoryIntegrationTest {

    private static final String OWNER_ID = "1234";
    private static final String STREET = "Florianska";
    private static final String POSTAL_CODE = "12-345";
    private static final String HOUSE_NUMBER = "1";
    private static final String APARTMENT_NUMBER = "13";
    private static final String CITY = "Cracow";
    private static final String COUNTRY = "Poland";
    private static final String DESCRIPTION = "Nice place to stay";
    private static final Map<String, Double> ROOM_DEFINITION = ImmutableMap.of(
            "Toilet", 10.0, "Bedroom", 30.0
    );


    @Autowired
    private ApartmentRepository apartmentRepository;

    @Test
    void shouldThrowExceptionWhenApartmentDoesNotExist() {
        String id = UUID.randomUUID().toString();

        ApartmentNotFoundException actual = assertThrows(ApartmentNotFoundException.class, () -> {
            apartmentRepository.findById(id);
        });

        assertThat(actual).hasMessage("Apartment with id " + id + " does not exist");
    }

    @Test
    @Transactional
    void shouldReturnExistingApartment() {
        Apartment apartment = createApartment();
        String existingId = apartmentRepository.save(apartment);

        Apartment actual = apartmentRepository.findById(existingId);

        ApartmentAssertion.assertThat(actual)
                .hasOwnerIdEqualsTo(OWNER_ID)
                .hasDescriptionEqualsTo(DESCRIPTION)
                .hasAddressEqualsTo(STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY)
                .hasRoomsEqualsTo(ROOM_DEFINITION);
    }

    @Test
    @Transactional
    void shouldReturnExistingApartmentWeWant() {
        Apartment apartment1 = apartment()
                .withOwnerId("1234")
                .withStreet("Florianska")
                .withPostalCode("98-765")
                .withHouseNumber("12")
                .withApartmentNumber("34")
                .withCity("Krakow")
                .withCountry("Poland")
                .withDescription("The Greatest")
                .withRoomsDefinition(ImmutableMap.of("Room1", 50.0))
                .build();
        apartmentRepository.save(apartment1);
        Apartment apartment2 = apartment()
                .withOwnerId("1234")
                .withStreet("Florianska")
                .withPostalCode("98-765")
                .withHouseNumber("12")
                .withApartmentNumber("34")
                .withCity("Krakow")
                .withCountry("Poland")
                .withDescription("The Greatest")
                .withRoomsDefinition(ImmutableMap.of("Room1", 50.0))
                .build();
        apartmentRepository.save(apartment2);
        Apartment apartment3 = apartment()
                .withOwnerId("1234")
                .withStreet("Florianska")
                .withPostalCode("98-765")
                .withHouseNumber("12")
                .withApartmentNumber("34")
                .withCity("Krakow")
                .withCountry("Poland")
                .withDescription("The Greatest")
                .withRoomsDefinition(ImmutableMap.of("Room1", 50.0))
                .build();
        apartmentRepository.save(apartment3);
        Apartment apartment = createApartment();
        String existingId = apartmentRepository.save(apartment);

        Apartment actual = apartmentRepository.findById(existingId);

        ApartmentAssertion.assertThat(actual)
                .hasOwnerIdEqualsTo(OWNER_ID)
                .hasDescriptionEqualsTo(DESCRIPTION)
                .hasAddressEqualsTo(STREET, POSTAL_CODE, HOUSE_NUMBER, APARTMENT_NUMBER, CITY, COUNTRY)
                .hasRoomsEqualsTo(ROOM_DEFINITION);
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
                .withRoomsDefinition(ROOM_DEFINITION)
                .build();
    }
}