package com.jkolacz.rentalapplication.domain.apartment;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Embeddable
class Room {

    private final String name;
    @Embedded
    private final SquareMeter squareMeter;

    Room(String name, SquareMeter squareMeter) {

        this.name = name;
        this.squareMeter = squareMeter;
    }
}
