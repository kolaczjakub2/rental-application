package com.jkolacz.rentalapplication.application.hotelRoom;

import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomFactory;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomRepository;

import java.util.Map;

public class HotelRoomApplicationService {

    private final HotelRoomRepository hotelRoomRepository;

    public HotelRoomApplicationService(HotelRoomRepository hotelRoomRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
    }

    public void add(String hotelId, Integer number, String description, Map<String, Double> roomsDefinition) {
        HotelRoom hotelRoom = new HotelRoomFactory().create(hotelId, number, description, roomsDefinition);

        hotelRoomRepository.save(hotelRoom);
    }

}
