package com.jkolacz.rentalapplication.query.hotel;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelFactory;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static java.util.Arrays.asList;

@SpringBootTest
class QueryHotelRepositoryTest {
    private static final String NAME_1 = "Great hotel";
    private static final String STREET_1 = "Florianska";
    private static final String POSTAL_CODE_1 = "12-345";
    private static final String BUILDING_NUMBER_1 = "1";
    private static final String CITY_1 = "Cracow";
    private static final String COUNTRY_1 = "Poland";
    private static final String NAME_2 = "Even greater hotel";
    private static final String STREET_2 = "Grodzka";
    private static final String POSTAL_CODE_2 = "54-321";
    private static final String BUILDING_NUMBER_2 = "13";
    private static final String CITY_2 = "Berlin";
    private static final String COUNTRY_2 = "Germany";

    private final HotelFactory hotelFactory = new HotelFactory();

    @Autowired
    private QueryHotelRepository queryHotelRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private SpringQueryHotelRepository jpaRepository;
    private String hotelId1;
    private String hotelId2;

    @AfterEach
    void deleteHotels() {
        jpaRepository.deleteAllById(asList(UUID.fromString(hotelId1), UUID.fromString(hotelId2)));
    }

    @Test
    void shouldFindAllHotels() {
        Hotel hotel1 = hotelFactory.create(NAME_1, STREET_1, BUILDING_NUMBER_1, POSTAL_CODE_1, CITY_1, COUNTRY_1);
        hotelId1 = hotelRepository.save(hotel1);
        Hotel hotel2 = hotelFactory.create(NAME_2, STREET_2, BUILDING_NUMBER_2, POSTAL_CODE_2, CITY_2, COUNTRY_2);
        hotelId2 = hotelRepository.save(hotel2);

        Iterable<HotelReadModel> actual = queryHotelRepository.findAll();

        Assertions.assertThat(actual)
                .hasSize(2)
                .anySatisfy(hotelReadModel -> {
                    HotelReadModelAssertion.assertThat(hotelReadModel)
                            .hasIdEqualsTo(hotelId1)
                            .hasNameEqualsTo(NAME_1)
                            .hasAddressEqualsTo(STREET_1, POSTAL_CODE_1, BUILDING_NUMBER_1, CITY_1, COUNTRY_1);
                })
                .anySatisfy(hotelReadModel -> {
                    HotelReadModelAssertion.assertThat(hotelReadModel)
                            .hasIdEqualsTo(hotelId2)
                            .hasNameEqualsTo(NAME_2)
                            .hasAddressEqualsTo(STREET_2, POSTAL_CODE_2, BUILDING_NUMBER_2, CITY_2, COUNTRY_2);
                });
    }

}