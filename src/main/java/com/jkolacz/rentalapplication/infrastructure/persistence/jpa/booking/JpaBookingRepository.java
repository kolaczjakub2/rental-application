package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.booking;

import com.jkolacz.rentalapplication.domain.apartment.Booking;
import com.jkolacz.rentalapplication.domain.apartment.BookingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaBookingRepository implements BookingRepository {

    private final SpringJpaBookingRepositoryImpl springJpaBookingRepository;

    public JpaBookingRepository(SpringJpaBookingRepositoryImpl springJpaBookingRepository) {
        this.springJpaBookingRepository = springJpaBookingRepository;
    }

    @Override
    public void save(Booking booking) {
        springJpaBookingRepository.save(booking);
    }

    @Override
    public Booking findById(String bookingId) {
        return springJpaBookingRepository.findById(UUID.fromString(bookingId)).get();
    }
}
