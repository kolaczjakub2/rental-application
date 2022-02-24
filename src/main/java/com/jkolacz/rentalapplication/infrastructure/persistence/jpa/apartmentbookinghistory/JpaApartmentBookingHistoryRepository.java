package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaApartmentBookingHistoryRepository implements ApartmentBookingHistoryRepository {
    private final SpringJpaApartmentBookingHistoryRepository springJpaApartmentBookingHistoryRepository;

    JpaApartmentBookingHistoryRepository(SpringJpaApartmentBookingHistoryRepository springJpaApartmentBookingHistoryRepository) {
        this.springJpaApartmentBookingHistoryRepository = springJpaApartmentBookingHistoryRepository;
    }

    @Override
    public boolean existFor(String apartmentId) {
        return springJpaApartmentBookingHistoryRepository.existsById(apartmentId);
    }

    @Override
    public ApartmentBookingHistory findFor(String apartmentId) {
        return springJpaApartmentBookingHistoryRepository.findById(apartmentId).get();
    }

    @Override
    public void save(ApartmentBookingHistory apartmentBookingHistory) {
        springJpaApartmentBookingHistoryRepository.save(apartmentBookingHistory);
    }
}
