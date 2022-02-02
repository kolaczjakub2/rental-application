package com.jkolacz.rentalapplication.infrastructure.persistence.jpa.hotelRoom;

import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomRepository;

public class HotelRoomJpaRepository implements HotelRoomRepository {

    private final SpringHotelRoomJpaRepository springHotelRoomJpaRepository;

    public HotelRoomJpaRepository(SpringHotelRoomJpaRepository springHotelRoomJpaRepository) {
        this.springHotelRoomJpaRepository = springHotelRoomJpaRepository;
    }

    @Override
    public void save(HotelRoom hotelRoom) {

    }

    @Override
    public HotelRoom findById(String id) {
        return null;
    }
}
