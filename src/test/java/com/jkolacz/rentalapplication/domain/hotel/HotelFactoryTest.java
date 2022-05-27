package com.jkolacz.rentalapplication.domain.hotel;

import org.junit.jupiter.api.Test;

import static com.jkolacz.rentalapplication.domain.hotel.Hotel.Builder.hotel;

class HotelFactoryTest {

    public static final String NAME = "hotel1";
    public static final String STREET = "street";
    public static final String BUILDING_NUMBER = "50A/2";
    public static final String POSTAL_CODE = "32-065";
    public static final String CITY = "Krzeszowice";
    public static final String COUNTRY = "Poland";

    @Test
    void shouldReturnNewHotelWithAllRequiredFields(){
        Hotel actual = hotel()
                .withName(NAME)
                .withStreet(STREET)
                .withPostalCode(BUILDING_NUMBER)
                .withBuildingNumber(POSTAL_CODE)
                .withCity(CITY)
                .withCountry(COUNTRY)
                .build();

        HotelAssertion.assertThat(actual)
                .hasNameEqualsTo(NAME)
                .hasAddressEqualsTo(STREET, BUILDING_NUMBER, POSTAL_CODE, CITY, COUNTRY);

    }
}