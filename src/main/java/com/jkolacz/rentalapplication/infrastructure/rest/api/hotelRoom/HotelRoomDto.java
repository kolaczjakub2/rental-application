package com.jkolacz.rentalapplication.infrastructure.rest.api.hotelRoom;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class HotelRoomDto {
    private final String hotelId;
    private final Integer number;
    private final String description;
    private final Map<String, Double> roomsDefinition;
}
