package com.jkolacz.rentalapplication.domain.apartment;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
@Table(name = "APARTMENT_ROOM")
class Room {
    @Id
    private String id;
    private final String name;
    @Embedded
    private final SquareMeter squareMeter;

    Room(String name, SquareMeter squareMeter) {

        this.name = name;
        this.squareMeter = squareMeter;
    }
}
