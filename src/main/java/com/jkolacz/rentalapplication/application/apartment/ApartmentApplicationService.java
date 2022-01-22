package com.jkolacz.rentalapplication.application.apartment;

import com.jkolacz.rentalapplication.doimain.apartment.Address;
import com.jkolacz.rentalapplication.doimain.apartment.Apartment;
import com.jkolacz.rentalapplication.doimain.apartment.Room;
import com.jkolacz.rentalapplication.doimain.apartment.SquareMeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApartmentApplicationService {

    public void add(String ownerId, String street, String postalCode, String houseNumber, String apartmentNumber,
                    String city, String country, String description, Map<String, Double> roomsDefinition) {

        Address address = new Address(street, postalCode, houseNumber, apartmentNumber, city, country);
        List<Room> rooms = new ArrayList<>();
        roomsDefinition.forEach((name, size) ->
                rooms.add(new Room(name, new SquareMeter(size))));
        Apartment apartment = new Apartment(ownerId, address, description,rooms);

    }
}
