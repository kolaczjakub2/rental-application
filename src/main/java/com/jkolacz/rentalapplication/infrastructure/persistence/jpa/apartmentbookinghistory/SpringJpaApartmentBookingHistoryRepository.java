package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartmentbookinghistory;

import com.jkolacz.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import org.springframework.data.repository.CrudRepository;

public interface SpringJpaApartmentBookingHistoryRepository extends CrudRepository<ApartmentBookingHistory, String> {
}
