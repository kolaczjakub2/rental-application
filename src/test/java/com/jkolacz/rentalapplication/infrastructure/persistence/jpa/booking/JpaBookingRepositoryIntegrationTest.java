package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.booking;

import com.jkolacz.rentalapplication.domain.booking.Booking;
import com.jkolacz.rentalapplication.domain.booking.BookingAssertion;
import com.jkolacz.rentalapplication.rentalapplication.infrastructure.persistence.jpa.booking.JpaBookingRepository;
import com.jkolacz.rentalapplication.rentalapplication.infrastructure.persistence.jpa.booking.SpringJpaBookingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaBookingRepositoryIntegrationTest {
    @Autowired private JpaBookingRepository repository;
    @Autowired private SpringJpaBookingRepository jpaRepository;
    private String bookingId;

    @AfterEach
    void deleteBooking() {
        jpaRepository.deleteById(UUID.fromString(bookingId));
    }

    @Test
    @Transactional
    void shouldFindExistingBooking() {
        List<LocalDate> days = asList(LocalDate.of(2020, 6, 1), LocalDate.of(2020, 6, 2), LocalDate.of(2020, 6, 4));
        String rentalPlaceId = randomId();
        String tenantId = randomId();
        Booking booking = Booking.hotelRoom(rentalPlaceId, tenantId, days);
        bookingId = repository.save(booking);

        Booking actual = repository.findById(bookingId);

        BookingAssertion.assertThat(actual)
                .isOpen()
                .isHotelRoom()
                .hasRentalPlaceIdEqualTo(rentalPlaceId)
                .hasTenantIdEqualTo(tenantId)
                .containsAllDays(days);
    }

    private String randomId() {
        return UUID.randomUUID().toString();
    }
}