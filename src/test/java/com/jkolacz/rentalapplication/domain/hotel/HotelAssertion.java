package com.jkolacz.rentalapplication.domain.hotel;


import org.assertj.core.api.Assertions;

public class HotelAssertion {
    private Hotel actual;

    public HotelAssertion(Hotel actual) {
        this.actual = actual;
    }

    static HotelAssertion assertThat(Hotel actual) {
        return new HotelAssertion(actual);
    }

    public HotelAssertion hasNameEqualsTo(String name) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("name", name);
        return this;
    }

    public HotelAssertion hasAddressEqualsTo(String street, String buildingNumber, String postalCode, String city, String country) {
        Assertions.assertThat(actual).extracting("address")
                .hasFieldOrPropertyWithValue("street",street)
                .hasFieldOrPropertyWithValue("buildingNumber",buildingNumber)
                .hasFieldOrPropertyWithValue("postalCode",postalCode)
                .hasFieldOrPropertyWithValue("city",city)
                .hasFieldOrPropertyWithValue("country",country);
        return this;
    }
}
