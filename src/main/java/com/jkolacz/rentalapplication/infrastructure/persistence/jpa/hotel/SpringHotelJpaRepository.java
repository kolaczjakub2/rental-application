package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotel;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;

public interface SpringHotelJpaRepository extends CrudRepository<Hotel, String> {
}
