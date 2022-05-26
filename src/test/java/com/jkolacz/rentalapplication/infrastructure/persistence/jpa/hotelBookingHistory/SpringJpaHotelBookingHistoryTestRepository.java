package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelBookingHistory;

import org.springframework.stereotype.Repository;

@Repository
public class SpringJpaHotelBookingHistoryTestRepository {
    private final SpringJpaHotelBookingHistoryRepository repository;

    SpringJpaHotelBookingHistoryTestRepository(SpringJpaHotelBookingHistoryRepository repository) {
        this.repository = repository;
    }

    public void deleteById(String hotelId) {
        repository.deleteById(hotelId);
    }
}
