package com.jkolacz.rentalapplication.domain.hotel;

import org.junit.jupiter.api.Test;

class HotelFactoryTest {

    public static final String NAME = "hotel1";
    public static final String STREET = "street";
    public static final String BUILDING_NUMBER = "50A/2";
    public static final String POSTAL_CODE = "32-065";
    public static final String CITY = "Krzeszowice";
    public static final String COUNTRY = "Poland";

    @Test
    void shouldReturnNewHotelWithAllRequiredFields(){
        Hotel actual = new HotelFactory().create(NAME, STREET, BUILDING_NUMBER, POSTAL_CODE, CITY, COUNTRY);

        HotelAssertion.assertThat(actual)
                .hasNameEqualsTo(NAME)
                .hasAddressEqualsTo(STREET, BUILDING_NUMBER, POSTAL_CODE, CITY, COUNTRY);

    }
}