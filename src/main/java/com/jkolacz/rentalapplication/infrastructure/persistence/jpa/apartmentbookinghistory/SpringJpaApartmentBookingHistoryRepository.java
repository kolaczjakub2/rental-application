package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringJpaApartmentBookingHistoryRepository extends CrudRepository<ApartmentBookingHistory, String> {
}
