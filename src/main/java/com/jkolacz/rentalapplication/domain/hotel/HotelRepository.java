package com.jkolacz.rentalapplication.domain.hotel;

public interface HotelRepository {
    String save(Hotel hotel);

    Hotel findById(String hotelId);
}
