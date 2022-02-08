package com.jkolacz.rentalapplication.domain.apartment;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ApartmentTest {

    @Test
    public void shouldCreateApartmentWithRequiredFields() {
        String ownerId = "1234";
        String street = "Florianska";
        String postalCode = "12-345";
        String houseNumber = "1";
        String apartmentNumber = "13";
        String city = "Cracow";
        String country = "Poland";
        String description = "Nice place to stay";
        Map<String, Double> roomDefinition = ImmutableMap.of(
                "Toilet", 10.0, "Bedroom", 30.0
        );
        Apartment actual = new ApartmentFactory().create(
                ownerId, street, postalCode, houseNumber, apartmentNumber, city, country,
                description, roomDefinition);

        assertThatHasOwnerId(actual, ownerId);
        assertThatHasDescription(actual, description);
        assertThatHasAddress(actual, street, postalCode, houseNumber, apartmentNumber, city, country);
        assertThatHasRooms(actual, roomDefinition);

    }

    private void assertThatHasOwnerId(Apartment actual, String ownerId) {
        assertThat(actual).hasFieldOrPropertyWithValue("ownerId", ownerId);
    }

    private void assertThatHasDescription(Apartment actual, String description) {
        assertThat(actual).hasFieldOrPropertyWithValue("description", description);
    }

    private void assertThatHasAddress(Apartment actual, String street, String postalCode, String houseNumber, String apartmentNumber, String city, String country) {
        assertThat(actual).extracting("address")
                .hasFieldOrPropertyWithValue("street", street)
                .hasFieldOrPropertyWithValue("postalCode", postalCode)
                .hasFieldOrPropertyWithValue("houseNumber", houseNumber)
                .hasFieldOrPropertyWithValue("apartmentNumber", apartmentNumber)
                .hasFieldOrPropertyWithValue("city", city)
                .hasFieldOrPropertyWithValue("country", country);
    }

    private void assertThatHasRooms(Apartment actual, Map<String, Double> roomDefinition) {
        assertThat(actual).extracting("rooms").satisfies(roomsActual -> {
            List<Room> rooms = (List<Room>) roomsActual;
            assertThat(rooms).hasSize(roomDefinition.size());
            roomDefinition.forEach((name, squareMeter) -> {
                assertThat(rooms).anySatisfy(room -> {
                    assertThat(room)
                            .hasFieldOrPropertyWithValue("name", name)
                            .hasFieldOrPropertyWithValue("squareMeter.size", squareMeter);
                });
            });
        });
    }

}