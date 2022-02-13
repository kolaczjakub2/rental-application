package com.jkolacz.rentalapplication.domain.hotelRoom;

import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class HotelRoomAssertion {
    private HotelRoom actual;

    public HotelRoomAssertion(HotelRoom actual) {
        this.actual = actual;
    }

    public static HotelRoomAssertion assertThat(HotelRoom actual) {
        return new HotelRoomAssertion(actual);
    }

    public HotelRoomAssertion hasHotelIdEqualsTo(String hotelId) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("hotelId", hotelId);
        return this;
    }

    public HotelRoomAssertion hasNumberEqualsTo(Integer number) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("number", number);
        return this;
    }

    public HotelRoomAssertion hasDescriptionEqualsTo(String description) {
        Assertions.assertThat(actual).hasFieldOrPropertyWithValue("description", description);
        return this;
    }

    public HotelRoomAssertion hasSpacesDefinitionEqualTo(Map<String, Double> expected) {
        Assertions.assertThat(actual).extracting("spaces").satisfies(spacesActual -> {
            List<Space> spaces = (List<Space>) spacesActual;
            Assertions.assertThat(spaces).hasSize(expected.size());

            expected.forEach((name, squareMeter) -> {
                Assertions.assertThat(spaces).anySatisfy(hasSpaceThat(name, squareMeter));
            });
        });
        return this;
    }

    private Consumer<Space> hasSpaceThat(String name, Double squareMeter) {
        return space -> Assertions.assertThat(space)
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("squareMeter.value", squareMeter);
    }
}
