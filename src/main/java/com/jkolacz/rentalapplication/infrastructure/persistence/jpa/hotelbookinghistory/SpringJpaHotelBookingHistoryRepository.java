package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelbookinghistory;

import com.jkolacz.rentalapplication.domain.hotelbookinghistory.HotelBookingHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringJpaHotelBookingHistoryRepository extends CrudRepository<HotelBookingHistory, String> {
}
