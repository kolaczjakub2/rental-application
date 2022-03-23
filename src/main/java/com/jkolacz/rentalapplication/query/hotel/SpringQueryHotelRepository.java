package com.jkolacz.rentalapplication.query.hotel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringQueryHotelRepository extends CrudRepository<HotelReadModel, String> {
}
