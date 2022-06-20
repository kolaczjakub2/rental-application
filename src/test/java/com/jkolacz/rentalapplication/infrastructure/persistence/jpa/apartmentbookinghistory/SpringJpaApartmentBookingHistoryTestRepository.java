package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory.SpringJpaApartmentBookingHistoryRepository;
import com.jkolacz.rentalapplication.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory.SpringJpaApartmentBookingHistoryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SpringJpaApartmentBookingHistoryTestRepository {
    private final SpringJpaApartmentBookingHistoryRepository repository;

    SpringJpaApartmentBookingHistoryTestRepository(SpringJpaApartmentBookingHistoryRepository repository) {
        this.repository = repository;
    }

    public void deleteById(String apartmentId) {
        repository.deleteById(apartmentId);
    }
}