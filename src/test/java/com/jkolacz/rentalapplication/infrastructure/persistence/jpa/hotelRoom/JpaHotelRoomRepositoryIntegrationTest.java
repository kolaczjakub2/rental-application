package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelRoom;

import com.google.common.collect.ImmutableMap;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomAssertion;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomFactory;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaHotelRoomRepositoryIntegrationTest {

    private static final String HOTEL_ID = "5678";
    private static final int ROOM_NUMBER = 42;
    private static final ImmutableMap<String, Double> SPACES_DEFINITION = ImmutableMap.of("Room1", 30.0);
    private static final String DESCRIPTION = "This is very nice place";

    @Autowired
    private HotelRoomRepository repository;

    @Autowired
    private SpringJpaHotelRoomRepository jpaRepository;

    private UUID hotelRoomId;

    @AfterEach
    void deleteHotelRoom() {
        if (hotelRoomId != null) {
            jpaRepository.deleteById(hotelRoomId);
        }
    }

    @Test
    void shouldThrowExceptionWhenNoHotelRoomFound() {
        String id = UUID.randomUUID().toString();

        HotelRoomDoesNotExistException actual = assertThrows(HotelRoomDoesNotExistException.class, () -> repository.findById(id));

        assertThat(actual).hasMessage("Hotel Room with id " + id + " does not exist.");
    }

    @Test
    @Transactional
    void shouldFindExistingHotelRoom() {
        HotelRoom hotelRoom = createHotelRoom();
        hotelRoomId = UUID.fromString(repository.save(hotelRoom));

        HotelRoom actual = repository.findById(hotelRoomId.toString());

        HotelRoomAssertion.assertThat(actual)
                .hasHotelIdEqualsTo(HOTEL_ID)
                .hasNumberEqualsTo(ROOM_NUMBER)
                .hasSpacesDefinitionEqualTo(SPACES_DEFINITION)
                .hasDescriptionEqualsTo(DESCRIPTION);
    }

    private HotelRoom createHotelRoom() {
        return new HotelRoomFactory().create(HOTEL_ID, ROOM_NUMBER, DESCRIPTION, SPACES_DEFINITION);
    }
}