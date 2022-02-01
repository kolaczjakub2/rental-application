package com.jkolacz.rentalapplication.infrastructure.rest.api.hotel;

import com.jkolacz.rentalapplication.application.hotel.HotelApplicationService;
import com.jkolacz.rentalapplication.query.hotel.HotelReadModel;
import com.jkolacz.rentalapplication.query.hotel.QueryHotelRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hotel")
public class HotelRestController {

    private final HotelApplicationService hotelApplicationService;
    private final QueryHotelRepository queryHotelRepository;

    public HotelRestController(HotelApplicationService hotelApplicationService, QueryHotelRepository queryHotelRepository) {
        this.hotelApplicationService = hotelApplicationService;
        this.queryHotelRepository = queryHotelRepository;
    }


    @PostMapping
    public void add(@RequestBody HotelDto hotelDto) {
        hotelApplicationService.add(hotelDto.getName(), hotelDto.getStreet(), hotelDto.getBuildingNumber(),
                hotelDto.getPostalCode(), hotelDto.getCity(), hotelDto.getCountry());
    }

    @GetMapping
    public Iterable<HotelReadModel> findAll(){
        return queryHotelRepository.findAll();
    }
}
