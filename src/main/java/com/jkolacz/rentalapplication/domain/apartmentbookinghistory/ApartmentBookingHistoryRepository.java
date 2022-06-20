package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

public interface ApartmentBookingHistoryRepository {
    boolean existsFor(String apartmentId);

    ApartmentBookingHistory findFor(String apartmentId);

    void save(ApartmentBookingHistory apartmentBookingHistory);
}
