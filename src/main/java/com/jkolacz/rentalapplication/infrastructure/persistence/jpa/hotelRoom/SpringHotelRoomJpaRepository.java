package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelRoom;

import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringHotelRoomJpaRepository extends CrudRepository<HotelRoom, UUID> {
}
