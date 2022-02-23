package com.jkolacz.rentalapplication.domain.apartment;

import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;


public class ApartmentAssertion {
    private Apartment actual;

    ApartmentAssertion(Apartment actual) {
        this.actual = actual;
    }

    public static ApartmentAssertion assertThat(Apartment actual) {
        return new ApartmentAssertion(actual);
    }

    public ApartmentAssertion hasOwnerIdEqualsTo(String ownerId) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("ownerId", ownerId);
        return this;
    }

    public ApartmentAssertion hasDescriptionEqualsTo(String description) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("description", description);
        return this;
    }

    public ApartmentAssertion hasAddressEqualsTo(String street, String postalCode, String houseNumber, String apartmentNumber, String city, String country) {
        Assertions.assertThat(actual).extracting("address")
                .hasFieldOrPropertyWithValue("street", street)
                .hasFieldOrPropertyWithValue("postalCode", postalCode)
                .hasFieldOrPropertyWithValue("houseNumber", houseNumber)
                .hasFieldOrPropertyWithValue("apartmentNumber", apartmentNumber)
                .hasFieldOrPropertyWithValue("city", city)
                .hasFieldOrPropertyWithValue("country", country);
        return this;
    }

    public ApartmentAssertion hasRoomsEqualsTo(Map<String, Double> roomDefinition) {
        Assertions.assertThat(actual).extracting("rooms").satisfies(roomsActual -> {
            List<Room> rooms = (List<Room>) roomsActual;
            Assertions.assertThat(rooms).hasSize(roomDefinition.size());
            roomDefinition.forEach((name, squareMeter) -> {
                Assertions.assertThat(rooms).anySatisfy(room -> {
                    Assertions.assertThat(room)
                            .hasFieldOrPropertyWithValue("name", name)
                            .hasFieldOrPropertyWithValue("squareMeter.size", squareMeter);
                });
            });
        });
        return this;
    }
}
