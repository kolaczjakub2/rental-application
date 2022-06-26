package com.jkolacz.rentalapplication.domain.hotel;

public interface HotelRoomRepository {
    String save(HotelRoom hotelRoom);

    HotelRoom findById(String id);

    boolean existById(String hotelRoomId);
}
