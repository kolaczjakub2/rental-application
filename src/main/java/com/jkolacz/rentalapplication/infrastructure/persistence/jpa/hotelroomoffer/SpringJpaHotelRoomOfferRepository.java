package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelroomoffer;

import com.jkolacz.rentalapplication.domain.hotelroomoffer.HotelRoomOffer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringJpaHotelRoomOfferRepository extends CrudRepository<HotelRoomOffer, UUID> {
}
