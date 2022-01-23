package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelRoom;

import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoom;
import org.springframework.data.repository.CrudRepository;

public interface SpringHotelRoomJpaRepository extends CrudRepository<HotelRoom,String> {
}
