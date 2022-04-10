package com.jkolacz.rentalapplication.infrastructure.rest.api.hotelRoom;

import com.jkolacz.rentalapplication.application.hotelRoom.HotelRoomApplicationService;
import com.jkolacz.rentalapplication.query.hotelRoom.HotelRoomReadModel;
import com.jkolacz.rentalapplication.query.hotelRoom.QueryHotelRoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    public ResponseEntity<String> add(@RequestBody HotelRoomDto hotelRoomDto) {
        String id = hotelRoomApplicationService.add(
                hotelRoomDto.getHotelId(), hotelRoomDto.getNumber(), hotelRoomDto.getDescription(), hotelRoomDto.getRoomsDefinition());

        return ResponseEntity.created(URI.create("/hotelroom/" + id)).build();
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<String> book(@PathVariable String id, @RequestBody HotelBookingDto hotelBookingDto) {
        String bookingId = hotelRoomApplicationService.book(id, hotelBookingDto.getTenantId(), hotelBookingDto.getDays());

        return ResponseEntity.created(URI.create("/booking/" + bookingId)).build();
    }

    @GetMapping("/hotel/{hotelId}")
    public Iterable<HotelRoomReadModel> findAll(@PathVariable String hotelId) {
        return queryHotelRoomRepository.findAll(hotelId);
    }
}
