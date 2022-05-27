package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelAssertion;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.jkolacz.rentalapplication.domain.hotel.Hotel.Builder.hotel;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaHotelRepositoryIntegrationTest {
    private static final String NAME = "Great hotel";
    private static final String STREET = "Unknown";
    private static final String POSTAL_CODE = "12-345";
    private static final String BUILDING_NUMBER = "13";
    private static final String CITY = "Somewhere";
    private static final String COUNTRY = "Nowhere";

    @Autowired
    private HotelRepository repository;
    @Autowired
    private SpringHotelJpaRepository jpaRepository;
    private String hotelId;

    @AfterEach
    void deleteHotel(){
        jpaRepository.deleteById(UUID.fromString(hotelId));
    }

    @Test
    void shouldSaveHotel() {
        Hotel hotel = hotel()
                .withName(NAME)
                .withStreet(STREET)
                .withPostalCode(POSTAL_CODE)
                .withBuildingNumber(BUILDING_NUMBER)
                .withCity(CITY)
                .withCountry(COUNTRY)
                .build();

        hotelId = repository.save(hotel);

        HotelAssertion.assertThat(findBy(hotelId))
                .hasNameEqualsTo(NAME)
                .hasAddressEqualsTo(STREET, POSTAL_CODE,BUILDING_NUMBER, CITY, COUNTRY);
    }

    private Hotel findBy(String hotelId) {
        return jpaRepository.findById(UUID.fromString(hotelId)).get();
    }
}