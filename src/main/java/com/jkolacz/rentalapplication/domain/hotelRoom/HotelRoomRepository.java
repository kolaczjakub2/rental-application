package com.jkolacz.rentalapplication.domain.hotelRoom;

public interface HotelRoomRepository {
    String save(HotelRoom hotelRoom);

    HotelRoom findById(String id);
}
