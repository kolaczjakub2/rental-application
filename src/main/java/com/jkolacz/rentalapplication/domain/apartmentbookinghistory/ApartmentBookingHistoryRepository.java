package com.jkolacz.rentalapplication.domain.apartmentbookinghistory;

public interface ApartmentBookingHistoryRepository {
    void save(ApartmentBookingHistory apartmentBookingHistory);

    boolean existFor(String apartmentId);

    ApartmentBookingHistory findFor(String apartmentId);

}
