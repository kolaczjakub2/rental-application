package com.jkolacz.rentalapplication.doimain.apartment;

import java.util.List;

public class Apartment {
    private final String ownerId;
    private final Address address;
    private final String description;
    private final List<Room> rooms;

    public Apartment(String ownerId, Address address, String description, List<Room> rooms) {

        this.ownerId = ownerId;
        this.address = address;
        this.description = description;
        this.rooms = rooms;
    }
}
