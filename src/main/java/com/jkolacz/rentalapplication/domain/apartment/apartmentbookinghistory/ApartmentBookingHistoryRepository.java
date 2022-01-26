package com.jkolacz.rentalapplication.domain.apartment.apartmentbookinghistory;

public interface ApartmentBookingHistoryRepository {
    void save(ApartmentBookingHistory apartmentBookingHistory);

    boolean existFor(String apartmentId);

    ApartmentBookingHistory findFor(String apartmentId);

}
