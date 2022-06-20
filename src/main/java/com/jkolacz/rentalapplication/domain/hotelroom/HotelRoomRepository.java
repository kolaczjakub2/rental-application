package com.jkolacz.rentalapplication.domain.hotelroom;

public interface HotelRoomRepository {
    String save(HotelRoom hotelRoom);

    HotelRoom findById(String id);

    boolean existById(String hotelRoomId);
}
