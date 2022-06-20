package com.jkolacz.rentalapplication.domain.hotelbookinghistory;

public interface HotelBookingHistoryRepository {
    boolean existsFor(String hotelId);

    HotelBookingHistory findFor(String hotelId);

    void save(HotelBookingHistory hotelBookingHistory);
}
