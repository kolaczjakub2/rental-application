package com.jkolacz.rentalapplication.application.hotelBookingHistory;


import com.google.common.collect.ImmutableMap;
import com.jkolacz.rentalapplication.application.hotelRoom.HotelRoomApplicationService;
import com.jkolacz.rentalapplication.domain.hotelBookingHistory.HotelBookingHistory;
import com.jkolacz.rentalapplication.domain.hotelBookingHistory.HotelBookingHistoryAssertion;
import com.jkolacz.rentalapplication.domain.hotelBookingHistory.HotelBookingHistoryRepository;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomFactory;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomRepository;
import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelRoom.SpringJpaHotelRoomTestRepository;
import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelBookingHistory.SpringJpaHotelBookingHistoryTestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Arrays.asList;

@SpringBootTest
@Tag("IntegrationTest")
class HotelBookingHistoryEventListenerIntegrationTest {
    private static final String HOTEL_ID = UUID.randomUUID().toString();
    private static final int HOTEL_NUMBER = 13;
    private static final Map<String, Double> SPACES_DEFINITION = ImmutableMap.of("RoomOne", 20.0, "RoomTwo", 20.0);
    private static final String DESCRIPTION = "What a lovely place";

    @Autowired
    private HotelRoomApplicationService hotelRoomApplicationService;
    @Autowired private HotelBookingHistoryRepository hotelBookingHistoryRepository;
    @Autowired private SpringJpaHotelBookingHistoryTestRepository springJpaHotelBookingHistoryTestRepository;
    @Autowired private HotelRoomRepository hotelRoomRepository;
    @Autowired private SpringJpaHotelRoomTestRepository springJpaHotelRoomTestRepository;

    private String hotelRoomId;

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
        givenExistingHotelRoom();

        hotelRoomApplicationService.book(hotelRoomId, tenantId, days);
        HotelBookingHistory actual = hotelBookingHistoryRepository.findFor(HOTEL_ID);

        HotelBookingHistoryAssertion.assertThat(actual).hasHotelRoomBookingHistoryFor(hotelRoomId, tenantId, days);
    }

    private void givenExistingHotelRoom() {
        hotelRoomId = hotelRoomRepository.save(createHotelRoom());
    }

    private HotelRoom createHotelRoom() {
        return new HotelRoomFactory().create(HOTEL_ID, HOTEL_NUMBER,DESCRIPTION, SPACES_DEFINITION);
    }
}