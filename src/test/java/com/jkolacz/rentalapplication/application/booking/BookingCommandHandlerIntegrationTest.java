package com.jkolacz.rentalapplication.application.booking;

import com.jkolacz.rentalapplication.domain.booking.Booking;
import com.jkolacz.rentalapplication.domain.booking.BookingAssertion;
import com.jkolacz.rentalapplication.domain.booking.BookingRepository;
import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.booking.SpringJpaBookingTestRepository;
import com.jkolacz.rentalapplication.infrastructure.rest.api.booking.BookingRestController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static java.util.Arrays.asList;

@SpringBootTest
@Tag("IntegrationTest")
class BookingCommandHandlerIntegrationTest {
    @Autowired private BookingRestController controller;
    @Autowired private BookingRepository bookingRepository;
    @Autowired private SpringJpaBookingTestRepository springJpaBookingTestRepository;

    private String bookingId;

    @AfterEach
    void removeBookings() {
        springJpaBookingTestRepository.deleteById(bookingId);
    }

    @Test
    void shouldAcceptBooking() {
        givenOpenBooking();

        controller.accept(bookingId);
        Booking actual = bookingRepository.findById(bookingId);

        BookingAssertion.assertThat(actual).isAccepted();
    }

    @Test
    void shouldRejectBooking() {
        givenOpenBooking();

        controller.reject(bookingId);
        Booking actual = bookingRepository.findById(bookingId);

        BookingAssertion.assertThat(actual).isRejected();
    }

    private void givenOpenBooking() {
        Booking booking = Booking.hotelRoom("1234", "5678", asList(LocalDate.now(), LocalDate.now().plusDays(1)));
        bookingId = bookingRepository.save(booking);
    }
}