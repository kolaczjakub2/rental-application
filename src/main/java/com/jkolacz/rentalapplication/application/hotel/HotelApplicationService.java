package com.jkolacz.rentalapplication.application.hotel;

import com.jkolacz.rentalapplication.domain.hotel.Hotel;
import com.jkolacz.rentalapplication.domain.hotel.HotelFactory;

public class HotelApplicationService {

    public void add(String name,String street,String buildingNumber,String postalCode,String city,String country){
       Hotel hotel =  new HotelFactory().create(name, street, buildingNumber, postalCode, city, country);
    }

}
