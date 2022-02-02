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

    }

    @Override
    public boolean existFor(String apartmentId) {
        return false;
    }

    @Override
    public ApartmentBookingHistory findFor(String apartmentId) {
        return null;
    }
}
