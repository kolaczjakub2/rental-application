package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;

public class JpaApartmentBookingHistoryRepository implements ApartmentBookingHistoryRepository {

    private final SpringJpaApartmentBookingHistoryRepository historyRepository;

    public JpaApartmentBookingHistoryRepository(SpringJpaApartmentBookingHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public void save(ApartmentBookingHistory apartmentBookingHistory) {
        historyRepository.save(apartmentBookingHistory);
    }

    @Override
    public boolean existFor(String apartmentId) {
        return historyRepository.existsById(apartmentId);
    }

    @Override
    public ApartmentBookingHistory findFor(String apartmentId) {
        return historyRepository.findById(apartmentId).get();
    }
}
