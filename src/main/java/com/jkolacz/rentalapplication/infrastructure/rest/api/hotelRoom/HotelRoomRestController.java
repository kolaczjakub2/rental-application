package com.jkolacz.rentalapplication.infrastructure.rest.api.hotelRoom;

import com.jkolacz.rentalapplication.application.hotelRoom.HotelRoomApplicationService;
import com.jkolacz.rentalapplication.query.hotelRoom.HotelRoomReadModel;
import com.jkolacz.rentalapplication.query.hotelRoom.QueryHotelRoomRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotelroom")
public class HotelRoomRestController {

    private final HotelRoomApplicationService hotelRoomApplicationService;
    private final QueryHotelRoomRepository queryHotelRoomRepository;

    public HotelRoomRestController(HotelRoomApplicationService hotelRoomApplicationService, QueryHotelRoomRepository queryHotelRoomRepository) {
        this.hotelRoomApplicationService = hotelRoomApplicationService;
        this.queryHotelRoomRepository = queryHotelRoomRepository;
    }

    @PostMapping
    public void add(@RequestBody HotelRoomDto hotelRoomDto) {
        hotelRoomApplicationService.add(hotelRoomDto.getHotelId(), hotelRoomDto.getNumber(),
                hotelRoomDto.getDescription(), hotelRoomDto.getRoomsDefinition());
    }

    @GetMapping("/hotel/{id}")
    public Iterable<HotelRoomReadModel> findAllByHotelId(@PathVariable String id){
        return queryHotelRoomRepository.findAll(id);
    }
}
