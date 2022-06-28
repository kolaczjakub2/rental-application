package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.booking;

import com.jkolacz.rentalapplication.domain.booking.Booking;
import com.jkolacz.rentalapplication.domain.booking.RentalType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface SpringJpaBookingRepository extends CrudRepository<Booking, UUID> {
    List<Booking> findAllByRentalTypeAndRentalPlaceId(RentalType rentalType, String rentalPlaceId);
}
