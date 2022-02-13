package com.jkolacz.rentalapplication.domain.hotelRoom;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

class HotelRoomFactoryTest {
    public static final String HOTEL_ID = "1234";
    public static final Integer NUMBER = 69;
    private static final Map<String, Double> SPACES_DEFINITION = ImmutableMap.of("RoomOne", 20.0, "RoomTwo", 20.0);
    public static final String DESCRIPTION = "50A/2";

    @Test
    void shouldReturnNewHotelRoomWithAllRequiredFields(){
        HotelRoom actual = new HotelRoomFactory().create(HOTEL_ID, NUMBER,DESCRIPTION, SPACES_DEFINITION);


        HotelRoomAssertion.assertThat(actual)
                .hasHotelIdEqualsTo(HOTEL_ID)
                .hasNumberEqualsTo(NUMBER)
                .hasDescriptionEqualsTo(DESCRIPTION)
                .hasSpacesDefinitionEqualTo(SPACES_DEFINITION);

    }
}