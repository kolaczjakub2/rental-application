package com.jkolacz.rentalapplication.infrastructure.rest.api.hotelroom;

import com.jkolacz.rentalapplication.application.hotel.HotelRoomBookingDto;
import com.jkolacz.rentalapplication.application.hotel.HotelRoomApplicationService;
import com.jkolacz.rentalapplication.application.hotel.HotelRoomDto;
import com.jkolacz.rentalapplication.query.hotelroom.HotelRoomReadModel;
import com.jkolacz.rentalapplication.query.hotelroom.QueryHotelRoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/hotelroom")
public class HotelRoomRestController {
    private final HotelRoomApplicationService hotelRoomApplicationService;
    private final QueryHotelRoomRepository queryHotelRoomRepository;

    public HotelRoomRestController(
            HotelRoomApplicationService hotelRoomApplicationService, QueryHotelRoomRepository queryHotelRoomRepository) {
        this.hotelRoomApplicationService = hotelRoomApplicationService;
        this.queryHotelRoomRepository = queryHotelRoomRepository;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody HotelRoomDto hotelRoomDto) {
        String id = hotelRoomApplicationService.add(hotelRoomDto);

        return ResponseEntity.created(URI.create("/hotelroom/" + id)).build();
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<String> book(@PathVariable String id, @RequestBody HotelRoomBookingDto hotelRoomBookingDto) {
        String bookingId = hotelRoomApplicationService.book(hotelRoomBookingDto);

        return ResponseEntity.created(URI.create("/booking/" + bookingId)).build();
    }

    @GetMapping("/hotel/{hotelId}")
    public Iterable<HotelRoomReadModel> findAll(@PathVariable String hotelId) {
        return queryHotelRoomRepository.findAll(UUID.fromString(hotelId));
    }
}
