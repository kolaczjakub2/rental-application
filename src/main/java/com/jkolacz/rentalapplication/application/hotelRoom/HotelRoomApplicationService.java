package com.jkolacz.rentalapplication.application.hotelRoom;

import com.jkolacz.rentalapplication.domain.eventchannel.EventChannel;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoom;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomFactory;
import com.jkolacz.rentalapplication.domain.hotelRoom.HotelRoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class HotelRoomApplicationService {

    private final HotelRoomRepository hotelRoomRepository;
    private final EventChannel eventChannel;


    public HotelRoomApplicationService(HotelRoomRepository hotelRoomRepository, EventChannel eventChannel) {
        this.hotelRoomRepository = hotelRoomRepository;
        this.eventChannel = eventChannel;
    }

    public void add(String hotelId, Integer number, String description, Map<String, Double> roomsDefinition) {
        HotelRoom hotelRoom = new HotelRoomFactory().create(hotelId, number, description, roomsDefinition);
        hotelRoomRepository.save(hotelRoom);
    }

    public void book(String id, String tenantId, List<LocalDate> days){
        HotelRoom hotelRoom = hotelRoomRepository.findById(id);
        hotelRoom.book(tenantId,days,eventChannel);
    }

}
