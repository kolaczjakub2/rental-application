package com.jkolacz.rentalapplication.application.hotelbookinghistory;

import com.google.common.collect.ImmutableMap;
import com.jkolacz.rentalapplication.application.hotel.HotelRoomApplicationService;
import com.jkolacz.rentalapplication.application.hotel.HotelRoomBookingDto;
import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotel.HotelRoomRepository;
import com.jkolacz.rentalapplication.domain.hotelbookinghistory.HotelBookingHistory;
import com.jkolacz.rentalapplication.domain.hotelbookinghistory.HotelBookingHistoryAssertion;
import com.jkolacz.rentalapplication.domain.hotelbookinghistory.HotelBookingHistoryRepository;
import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelbookinghistory.SpringJpaHotelBookingHistoryTestRepository;
import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelroom.SpringJpaHotelRoomTestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.jkolacz.rentalapplication.domain.hotel.Hotel.Builder.hotel;
import static com.jkolacz.rentalapplication.domain.hotel.HotelRoom.Builder.hotelRoom;
import static java.util.Arrays.asList;

@SpringBootTest
@Tag("IntegrationTest")
class HotelBookingHistoryEventListenerIntegrationTest {
    private static final String HOTEL_ID = UUID.randomUUID().toString();
    private static final int HOTEL_NUMBER = 13;
    private static final Map<String, Double> SPACES_DEFINITION = ImmutableMap.of("RoomOne", 20.0, "RoomTwo", 20.0);
    private static final String DESCRIPTION = "What a lovely place";

    @Autowired private HotelRoomApplicationService hotelRoomApplicationService;
    @Autowired private HotelBookingHistoryRepository hotelBookingHistoryRepository;
    @Autowired private SpringJpaHotelBookingHistoryTestRepository springJpaHotelBookingHistoryTestRepository;
    @Autowired private HotelRoomRepository hotelRoomRepository;
    @Autowired private SpringJpaHotelRoomTestRepository springJpaHotelRoomTestRepository;
    @Autowired private HotelRepository hotelRepository;

    private String hotelRoomId;
    private String hotelId;

    @BeforeEach
    void givenExistingHotelRoom() {
        Hotel hotel = hotel().withName("Great hotel").build();
        hotelId = hotelRepository.save(hotel);

        hotel.addRoom(HOTEL_NUMBER, SPACES_DEFINITION, DESCRIPTION);
        hotelRepository.save(hotel);
    }

    @AfterEach
    void removeHotelRoom() {
        springJpaHotelRoomTestRepository.deleteById(hotelRoomId);
        springJpaHotelBookingHistoryTestRepository.deleteById(HOTEL_ID);
    }

    @Test
    @Transactional
    void shouldUpdateHotelBookingHistory() {
        String tenantId = "11223344";
        List<LocalDate> days = asList(LocalDate.of(2020, 1, 13), LocalDate.of(2020, 1, 14));
        HotelRoomBookingDto hotelRoomBookingDto = new HotelRoomBookingDto(hotelId, HOTEL_NUMBER, tenantId, days);

        hotelRoomApplicationService.book(hotelRoomBookingDto);
        HotelBookingHistory actual = hotelBookingHistoryRepository.findFor(HOTEL_ID);

        HotelBookingHistoryAssertion.assertThat(actual).hasHotelRoomBookingHistoryFor(hotelRoomId, tenantId, days);
    }


    private HotelRoom createHotelRoom() {
        return hotelRoom()
                .withHotelId(HOTEL_ID)
                .withNumber(HOTEL_NUMBER)
                .withSpacesDefinition(SPACES_DEFINITION)
                .withDescription(DESCRIPTION)
                .build();
    }
}