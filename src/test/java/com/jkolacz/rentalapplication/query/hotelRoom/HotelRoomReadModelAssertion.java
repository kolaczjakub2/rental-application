package com.jkolacz.rentalapplication.query.hotelRoom;

import com.google.common.collect.ImmutableMap;
import com.jkolacz.rentalapplication.domain.hotelRoom.Space;
import org.assertj.core.api.Assertions;

import java.util.function.Consumer;

class HotelRoomReadModelAssertion {
    private final HotelRoomReadModel actual;

    private HotelRoomReadModelAssertion(HotelRoomReadModel actual) {
        this.actual = actual;
    }

    static HotelRoomReadModelAssertion assertThat(HotelRoomReadModel actual) {
        return new HotelRoomReadModelAssertion(actual);
    }

    HotelRoomReadModelAssertion hasHotelRoomIdEqualTo(String expected) {
        Assertions.assertThat(actual.getId()).isEqualTo(expected);
        return this;
    }

    HotelRoomReadModelAssertion hasHotelIdEqualTo(String expected) {
        Assertions.assertThat(actual.getHotelId()).isEqualTo(expected);
        return this;
    }

    HotelRoomReadModelAssertion hasNumberEqualTo(int expected) {
        Assertions.assertThat(actual.getNumber()).isEqualTo(expected);
        return this;
    }

    HotelRoomReadModelAssertion hasSpacesDefinitionEqualTo(ImmutableMap<String, Double> expected) {
        Assertions.assertThat(actual.getSpaces()).hasSize(expected.size());

        expected.forEach((name, squareMeter) -> {
            Assertions.assertThat(actual.getSpaces()).anySatisfy(hasSpaceThat(name, squareMeter));
        });

        return this;
    }

    private Consumer<Space> hasSpaceThat(String name, Double squareMeter) {
        return space -> {
            Assertions.assertThat(space.getName()).isEqualTo(name);
//            Assertions.assertThat(space.getSquareMeter()).isEqualTo(squareMeter);
        };
    }

    HotelRoomReadModelAssertion hasDescriptionEqualTo(String expected) {
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected);
        return this;
    }
}