package com.jkolacz.rentalapplication.infrastructure.rest.api.hotelRoom;

import com.jkolacz.rentalapplication.application.hotelRoom.HotelRoomApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelRoomRestController {

    private final HotelRoomApplicationService hotelRoomApplicationService;

    public HotelRoomRestController(HotelRoomApplicationService hotelRoomApplicationService) {
        this.hotelRoomApplicationService = hotelRoomApplicationService;
    }

    @PostMapping
    public void add(@RequestBody HotelRoomDto hotelRoomDto) {
        hotelRoomApplicationService.add(hotelRoomDto.getHotelId(), hotelRoomDto.getNumber(),
                hotelRoomDto.getDescription(), hotelRoomDto.getRoomsDefinition());
    }
}
