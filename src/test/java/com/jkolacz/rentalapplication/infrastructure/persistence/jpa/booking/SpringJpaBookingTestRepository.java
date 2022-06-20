package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.booking;

import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.booking.SpringJpaBookingRepository;
import com.jkolacz.rentalapplication.rentalapplication.infrastructure.persistence.jpa.booking.SpringJpaBookingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SpringJpaBookingTestRepository {
    private final SpringJpaBookingRepository repository;

    SpringJpaBookingTestRepository(SpringJpaBookingRepository repository) {
        this.repository = repository;
    }

    public void deleteById(String bookingId) {
        repository.deleteById(UUID.fromString(bookingId));
    }
}