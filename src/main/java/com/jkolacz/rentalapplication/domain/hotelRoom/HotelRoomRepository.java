package com.jkolacz.rentalapplication.domain.hotelRoom;

public interface HotelRoomRepository {
    void save(HotelRoom hotelRoom);

    HotelRoom findById(String id);
}
