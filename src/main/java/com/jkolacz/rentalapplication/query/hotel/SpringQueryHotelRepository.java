package com.jkolacz.rentalapplication.query.hotel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringQueryHotelRepository extends CrudRepository<HotelReadModel, UUID> {
}
