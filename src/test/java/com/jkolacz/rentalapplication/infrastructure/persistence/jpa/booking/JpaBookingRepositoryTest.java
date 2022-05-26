package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.booking;

import com.jkolacz.rentalapplication.domain.apartment.Booking;
import com.jkolacz.rentalapplication.domain.apartment.BookingAssertion;
import com.jkolacz.rentalapplication.domain.apartment.BookingRepository;
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

    @Autowired
    private BookingRepository repository;
    @Autowired
    private SpringJpaBookingRepository jpaRepository;
    private String bookingId;

    @AfterEach
    void deleteBooking(){
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
                .containsAllDays(days)
                .hasTenantIdEqualTo(tenantId)
                .hasRentalPlaceIdEqualTo(rentalPlaceId);

    }

    private String randomId() {
        return UUID.randomUUID().toString();
    }


}