package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelbookinghistory;

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