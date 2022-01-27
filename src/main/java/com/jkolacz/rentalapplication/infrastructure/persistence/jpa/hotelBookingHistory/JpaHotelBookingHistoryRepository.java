package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelBookingHistory;

import com.jkolacz.rentalapplication.domain.hotelRoomBookingHistory.HotelBookingHistory;
import com.jkolacz.rentalapplication.domain.hotelRoomBookingHistory.HotelBookingHistoryRepository;

public class JpaHotelBookingHistoryRepository  implements HotelBookingHistoryRepository {
    private final SpringJpaHotelBookingHistoryRepository springJpaHotelBookingHistoryRepository;

    JpaHotelBookingHistoryRepository(SpringJpaHotelBookingHistoryRepository springJpaHotelBookingHistoryRepository) {
        this.springJpaHotelBookingHistoryRepository = springJpaHotelBookingHistoryRepository;
    }

    @Override
    public boolean existsFor(String hotelId) {
        return springJpaHotelBookingHistoryRepository.existsById(hotelId);
    }

    @Override
    public HotelBookingHistory findFor(String hotelId) {
        return springJpaHotelBookingHistoryRepository.findById(hotelId).get();
    }

    @Override
    public void save(HotelBookingHistory hotelBookingHistory) {
        springJpaHotelBookingHistoryRepository.save(hotelBookingHistory);
    }
}
