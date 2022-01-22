package com.jkolacz.rentalapplication.domain.hotelRoom;

import java.util.List;

public class HotelRoom {
    private final String hotelId;
    private final Integer number;
    private final String description;
    private final List<Space> rooms;

    HotelRoom(String hotelId, Integer number, String description, List<Space> rooms) {
        this.hotelId = hotelId;
        this.number = number;
        this.description = description;
        this.rooms = rooms;
    }
}
