package com.jkolacz.rentalapplication.domain.apartment;

import javax.persistence.*;
import java.util.List;

@Entity
public class Apartment {
    @Id
    @GeneratedValue
    private String id;
    private final String ownerId;

    @Embedded
    private final Address address;
    private final String description;
    @OneToMany
    private final List<Room> rooms;

    Apartment(String ownerId, Address address, String description, List<Room> rooms) {

        this.ownerId = ownerId;
        this.address = address;
        this.description = description;
        this.rooms = rooms;
    }

    public void book(String tenantId, Period period) {
        //publish an event
    }
}
