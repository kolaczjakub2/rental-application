package com.jkolacz.rentalapplication.query.hotelRoom;

import com.google.common.collect.ImmutableMap;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomFactory;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QueryHotelRoomRepositoryIntegrationTest {
    private static final String HOTEL_ID = "5678";
    private static final int ROOM_NUMBER_1 = 42;
    private static final ImmutableMap<String, Double> SPACES_DEFINITION_1 = ImmutableMap.of("Room1", 30.0);
    private static final String DESCRIPTION_1 = "This is very nice place";
    private static final int ROOM_NUMBER_2 = 13;
    private static final ImmutableMap<String, Double> SPACES_DEFINITION_2 = ImmutableMap.of("RoomOne", 10.0, "RoomTwo", 25.0);
    private static final String DESCRIPTION_2 = "This is even better place";
    private final HotelRoomFactory hotelRoomFactory = new HotelRoomFactory();

    @Autowired
    private HotelRoomRepository hotelRoomRepository;
    @Autowired
    private QueryHotelRoomRepository queryHotelRoomRepository;
    @Autowired
    private SpringQueryHotelRoomRepository springJpaHotelRoomTestRepository;
    private String hotelRoomId1;
    private String hotelRoomId2;

    @AfterEach
    void deleteHotelRooms() {
        springJpaHotelRoomTestRepository.deleteAllById(asList(hotelRoomId1, hotelRoomId2));
    }

    @Test
    @Transactional
    void shouldReturnAllHotelRooms() {
        HotelRoom hotelRoom1 = hotelRoomFactory.create(HOTEL_ID, ROOM_NUMBER_1, DESCRIPTION_1, SPACES_DEFINITION_1);
        hotelRoomId1 = hotelRoomRepository.save(hotelRoom1);
        HotelRoom hotelRoom2 = hotelRoomFactory.create(HOTEL_ID, ROOM_NUMBER_2, DESCRIPTION_2, SPACES_DEFINITION_2);
        hotelRoomId2 = hotelRoomRepository.save(hotelRoom2);

        Iterable<HotelRoomReadModel> actual = queryHotelRoomRepository.findAll(HOTEL_ID);

        assertThat(actual)
                .hasSize(2)
                .anySatisfy(hotelRoomReadModel -> {
                    HotelRoomReadModelAssertion.assertThat(hotelRoomReadModel)
                            .hasHotelRoomIdEqualTo(hotelRoomId1)
                            .hasHotelIdEqualTo(HOTEL_ID)
                            .hasNumberEqualTo(ROOM_NUMBER_1)
                            .hasSpacesDefinitionEqualTo(SPACES_DEFINITION_1)
                            .hasDescriptionEqualTo(DESCRIPTION_1);
                })
                .anySatisfy(hotelRoomReadModel -> {
                    HotelRoomReadModelAssertion.assertThat(hotelRoomReadModel)
                            .hasHotelRoomIdEqualTo(hotelRoomId2)
                            .hasHotelIdEqualTo(HOTEL_ID)
                            .hasNumberEqualTo(ROOM_NUMBER_2)
                            .hasSpacesDefinitionEqualTo(SPACES_DEFINITION_2)
                            .hasDescriptionEqualTo(DESCRIPTION_2);
                });
    }
}