package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelBookingHistory;

import com.jkolacz.rentalapplication.domain.hotelBookingHistory.HotelBookingHistory;
import com.jkolacz.rentalapplication.domain.hotelBookingHistory.HotelBookingHistoryRepository;

public class JpaHotelBookingHistoryRepository  implements HotelBookingHistoryRepository {
    private final SpringJpaHotelBookingHistoryRepository springJpaHotelBookingHistoryRepository;

    JpaHotelBookingHistoryRepository(SpringJpaHotelBookingHistoryRepository springJpaHotelBookingHistoryRepository) {
        this.springJpaHotelBookingHistoryRepository = springJpaHotelBookingHistoryRepository;
    }

    @Override
    public boolean existsFor(String hotelId) {
        return false;
    }

    @Override
    public HotelBookingHistory findFor(String hotelId) {
        return null;
    }

    @Override
    public void save(HotelBookingHistory hotelBookingHistory) {

    }
}
