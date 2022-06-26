package com.jkolacz.rentalapplication.query.hotelroom;

import com.google.common.collect.ImmutableMap;
import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomRepository;
import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotel.SpringJpaHotelTestRepository;
import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelroom.SpringJpaHotelRoomTestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.UUID;

import static com.jkolacz.rentalapplication.domain.hotel.Hotel.Builder.hotel;
import static com.jkolacz.rentalapplication.domain.hotel.HotelRoom.Builder.hotelRoom;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Tag("IntegrationTest")
class QueryHotelRoomRepositoryIntegrationTest {
    private static final int ROOM_NUMBER_1 = 42;
    private static final ImmutableMap<String, Double> SPACES_DEFINITION_1 = ImmutableMap.of("Room1", 30.0);
    private static final String DESCRIPTION_1 = "This is very nice place";
    private static final int ROOM_NUMBER_2 = 13;
    private static final ImmutableMap<String, Double> SPACES_DEFINITION_2 = ImmutableMap.of("RoomOne", 10.0, "RoomTwo", 25.0);
    private static final String DESCRIPTION_2 = "This is even better place";

    @Autowired private HotelRoomRepository hotelRoomRepository;
    @Autowired private QueryHotelRoomRepository queryHotelRoomRepository;
    @Autowired private SpringJpaHotelRoomTestRepository springJpaHotelRoomTestRepository;
    private String hotelRoomId1;
    private String hotelRoomId2;
    private String hotelId;

    @Autowired private SpringJpaHotelTestRepository springJpaHotelRepository;
    @Autowired private HotelRepository hotelRepository;

    @AfterEach
    void deleteHotelRooms() {
        springJpaHotelRoomTestRepository.deleteAll(asList(hotelRoomId1, hotelRoomId2));
    }

    @Test
    @Transactional
    void shouldReturnAllHotelRooms() {
        Hotel hotel = existingHotel();
        HotelRoom.Builder hotelRoom1 = hotelRoom()
                .withHotelId(hotelId)
                .withNumber(ROOM_NUMBER_1)
                .withSpacesDefinition(SPACES_DEFINITION_1)
                .withDescription(DESCRIPTION_1);
        hotelRoomId1 = existing(hotelRoom1);
        HotelRoom.Builder hotelRoom2 = hotelRoom()
                .withHotelId(hotelId)
                .withNumber(ROOM_NUMBER_2)
                .withSpacesDefinition(SPACES_DEFINITION_2)
                .withDescription(DESCRIPTION_2);
        hotelRoomId2 = existing(hotelRoom2);

        Iterable<HotelRoomReadModel> actual = queryHotelRoomRepository.findAll(UUID.fromString(hotelId));
        
        assertThat(actual)
                .hasSize(2)
                .anySatisfy(hotelRoomReadModel -> {
                    HotelRoomReadModelAssertion.assertThat(hotelRoomReadModel)
                            .hasHotelRoomIdEqualTo(hotelRoomId1)
                            .hasHotelIdEqualTo(hotelId)
                            .hasNumberEqualTo(ROOM_NUMBER_1)
                            .hasSpacesDefinitionEqualTo(SPACES_DEFINITION_1)
                            .hasDescriptionEqualTo(DESCRIPTION_1);
                })
                .anySatisfy(hotelRoomReadModel -> {
                    HotelRoomReadModelAssertion.assertThat(hotelRoomReadModel)
                            .hasHotelRoomIdEqualTo(hotelRoomId2)
                            .hasHotelIdEqualTo(hotelId)
                            .hasNumberEqualTo(ROOM_NUMBER_2)
                            .hasSpacesDefinitionEqualTo(SPACES_DEFINITION_2)
                            .hasDescriptionEqualTo(DESCRIPTION_2);
                });
    }

    private String existing(HotelRoom.Builder hotelRoom) {
        return hotelRoomRepository.save(hotelRoom.build());
    }

    private Hotel existingHotel() {
        Hotel hotel = hotel().build();
        hotelId = hotelRepository.save(hotel);
        return hotel;
    }
}