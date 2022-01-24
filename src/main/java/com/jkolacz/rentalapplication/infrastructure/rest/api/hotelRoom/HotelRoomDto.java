package com.jkolacz.rentalapplication.infrastructure.rest.api.hotelRoom;

import java.util.Map;

public class HotelRoomDto {
    private final String hotelId;
    private final Integer number;
    private final String description;
    private final Map<String, Double> roomsDefinition;

    public HotelRoomDto(String hotelId, Integer number, String description, Map<String, Double> roomsDefinition) {
        this.hotelId = hotelId;
        this.number = number;
        this.description = description;
        this.roomsDefinition = roomsDefinition;
    }

    public String getHotelId() {
        return hotelId;
    }

    public Integer getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Double> getRoomsDefinition() {
        return roomsDefinition;
    }
}
