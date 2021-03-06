package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.booking;

import com.jkolacz.rentalapplication.domain.booking.Booking;
import com.jkolacz.rentalapplication.domain.booking.RentalPlaceIdentifier;
import com.jkolacz.rentalapplication.domain.period.Period;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.jkolacz.rentalapplication.domain.booking.BookingAssertion.assertThat;
import static java.util.Arrays.asList;

@SpringBootTest
@Tag("DomainRepositoryIntegrationTest")
class JpaBookingRepositoryIntegrationTest {
    private static final List<LocalDate> DAYS = asList(LocalDate.of(2020, 6, 1), LocalDate.of(2020, 6, 2), LocalDate.of(2020, 6, 4));
    private static final String TENANT_ID = randomId();
    private static final Period PERIOD = new Period(LocalDate.now(), LocalDate.now().plusDays(1));

    @Autowired private JpaBookingRepository repository;
    @Autowired private SpringJpaBookingRepository jpaRepository;
    private final List<String> bookingIds = new ArrayList<>();

    @AfterEach
    void deleteBooking() {
        bookingIds.forEach(id -> jpaRepository.deleteById(UUID.fromString(id)));
    }

    @Test
    @Transactional
    void shouldFindExistingBooking() {
        String rentalPlaceId = randomId();
        Booking booking = Booking.hotelRoom(rentalPlaceId, TENANT_ID, DAYS);
        String bookingId = save(booking);

        Booking actual = repository.findById(bookingId);

        assertThat(actual)
                .isOpen()
                .isEqualToBookingHotelRoom(rentalPlaceId, TENANT_ID, DAYS);
    }

    @Test
    void shouldFindBookingsByRentalPlaceIdentifier() {
        String rentalPlaceId1 = randomId();
        String rentalPlaceId2 = randomId();
        Booking booking = Booking.hotelRoom(rentalPlaceId1, TENANT_ID, DAYS);
        String bookingId = save(booking);
        String bookingId1 = save(Booking.hotelRoom(rentalPlaceId1, TENANT_ID, DAYS));
        String bookingId2 = save(Booking.hotelRoom(rentalPlaceId1, TENANT_ID, DAYS));
        save(Booking.hotelRoom(rentalPlaceId2, TENANT_ID, DAYS));
        save(Booking.apartment(rentalPlaceId2, TENANT_ID, PERIOD));
        save(Booking.apartment(rentalPlaceId1, TENANT_ID, PERIOD));

        List<Booking> actual = repository.findAllBy(booking.rentalPlaceIdentifier());

        Assertions.assertThat(actual)
                .hasSize(3)
                .anySatisfy(actualBooking -> assertThat(actualBooking).hasIdEqualTo(bookingId))
                .anySatisfy(actualBooking -> assertThat(actualBooking).hasIdEqualTo(bookingId1))
                .anySatisfy(actualBooking -> assertThat(actualBooking).hasIdEqualTo(bookingId2));
    }

    @Test
    void shouldFindNoAcceptedBookingsByRentalPlaceIdentifier() {
        repository.findAllAcceptedBy(RentalPlaceIdentifier.apartment(randomId()));
    }

    private String save(Booking booking) {
        String bookingId = repository.save(booking);
        bookingIds.add(bookingId);

        return bookingId;
    }

    private static String randomId() {
        return UUID.randomUUID().toString();
    }
}