package com.jkolacz.rentalapplication.application.hotel;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelFactory;
import com.jkolacz.rentalapplication.domain.hotel.HotelRepository;
import org.springframework.stereotype.Component;

@Component
public class HotelApplicationService {

    private final HotelRepository hotelRepository;

    public HotelApplicationService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public String add(String name,String street,String buildingNumber,String postalCode,String city,String country){
       Hotel hotel =  new HotelFactory().create(name, street, buildingNumber, postalCode, city, country);

       return hotelRepository.save(hotel);
    }

}
