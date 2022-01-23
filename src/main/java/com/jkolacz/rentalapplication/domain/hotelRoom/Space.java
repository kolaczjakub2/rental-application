package com.jkolacz.rentalapplication.domain.hotelRoom;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
class Space {
    private final String name;
    @Embedded
    private final SquareMeter squareMeter;

    Space(String name, SquareMeter squareMeter) {
        this.name = name;
        this.squareMeter = squareMeter;
    }
}
