package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelBookingHistory;

import com.jkolacz.rentalapplication.domain.hotelBookingHistory.HotelBookingHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringJpaHotelBookingHistoryRepository extends CrudRepository<HotelBookingHistory, String> {
}
