package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.apartment;

import com.jkolacz.rentalapplication.domain.apartment.Apartment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringApartmentJpaRepository extends CrudRepository<Apartment, UUID> {
}
