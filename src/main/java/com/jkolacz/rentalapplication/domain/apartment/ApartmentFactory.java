package com.jkolacz.rentalapplication.domain.apartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApartmentFactory {
    @SuppressWarnings("checkstyle:ParameterNumber")
    public Apartment create(String ownerId, String street, String postalCode, String houseNumber, String apartmentNumber,
                            String city, String country, String description, Map<String, Double> roomsDefinition) {
        Address address = new Address(street, postalCode, houseNumber, apartmentNumber, city, country);
        List<Room> rooms = new ArrayList<>();
        roomsDefinition.forEach((name, size) ->
                rooms.add(new Room(name, new SquareMeter(size))));
        return new Apartment(ownerId, address, rooms, description);
    }
}
