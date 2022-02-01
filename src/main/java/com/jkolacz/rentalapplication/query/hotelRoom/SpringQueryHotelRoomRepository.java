package com.jkolacz.rentalapplication.query.hotelRoom;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpringQueryHotelRoomRepository extends CrudRepository<HotelRoomReadModel, String> {
    List<HotelRoomReadModel> findAllByHotelId(String hotelId);
}
