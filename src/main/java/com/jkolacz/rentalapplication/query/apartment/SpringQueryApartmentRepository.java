package com.jkolacz.rentalapplication.query.apartment;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpringQueryApartmentRepository extends CrudRepository<ApartmentReadModel, UUID> {
}
