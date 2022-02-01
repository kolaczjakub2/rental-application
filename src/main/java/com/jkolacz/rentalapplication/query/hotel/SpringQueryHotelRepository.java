package com.jkolacz.rentalapplication.query.hotel;

import org.springframework.data.repository.CrudRepository;

public interface SpringQueryHotelRepository extends CrudRepository<HotelReadModel, String> {
}
