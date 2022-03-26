package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelBookingHistory;

import com.jkolacz.rentalapplication.domain.hotelBookingHistory.HotelBookingHistory;
import com.jkolacz.rentalapplication.domain.hotelBookingHistory.HotelBookingHistoryAssertion;
import com.jkolacz.rentalapplication.domain.hotelBookingHistory.HotelBookingHistoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class JpaHotelBookingHistoryRepositoryTest {

    @Autowired
    private HotelBookingHistoryRepository repository;

    @Autowired
    private SpringJpaHotelBookingHistoryRepository jpaRepository;

    private String hotelId;


    @AfterEach
    void deleteHotelBookingHistory() {
        if (hotelId != null) {
            jpaRepository.deleteById(hotelId);
        }
    }


    @Test
    void shouldRecognizeHotelBookingHistoryDoesNotExist() {
        String id = randomId();

        Assertions.assertThat(repository.existsFor(id)).isFalse();
    }


    @Test
    void shouldRecognizeHotelBookingExist() {
        String id = randomId();

        jpaRepository.save(new HotelBookingHistory(id));

        Assertions.assertThat(repository.existsFor(id)).isTrue();
    }

    @Test
    @Transactional
    void shouldFindExistingHotelBookingHistory() {
        hotelId = randomId();
        String hotelRoomId = randomId();
        LocalDateTime bookingDateTime = LocalDateTime.now();
        String tenantId = randomId();
        List<LocalDate> days = List.of(LocalDate.now());
        HotelBookingHistory hotelBookingHistory = new HotelBookingHistory(hotelId);
        hotelBookingHistory.add(hotelRoomId, bookingDateTime, tenantId, days);
        repository.save(hotelBookingHistory);
        HotelBookingHistory actual = repository.findFor(hotelId);

        HotelBookingHistoryAssertion
                .assertThat(actual).hasHotelRoomBookingHistoryFor(hotelRoomId, bookingDateTime, tenantId, days);
    }

    private String randomId() {
        return UUID.randomUUID().toString();
    }
}