package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringHotelJpaRepository extends CrudRepository<Hotel, UUID> {
}
