package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelRoom;

import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface SpringJpaHotelRoomRepository extends CrudRepository<HotelRoom, UUID> {
}
