package com.jkolacz.rentalapplication.query.apartment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringQueryApartmentBookingHistoryRepository extends CrudRepository<ApartmentBookingHistoryReadModel, String> {
}
