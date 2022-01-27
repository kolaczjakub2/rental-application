package com.jkolacz.rentalapplication.domain.hotelRoomBookingHistory;

public interface HotelBookingHistoryRepository {
    boolean existsFor(String hotelId);

    HotelBookingHistory findFor(String hotelId);

    void save(HotelBookingHistory hotelBookingHistory);
}
