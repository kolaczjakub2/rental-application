package com.jkolacz.rentalapplication.domain.apartment;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
class Room {

    private String name;
    @Embedded
    private SquareMeter squareMeter;

    Room() {
    }

    Room(String name, SquareMeter squareMeter) {
        this.name = name;
        this.squareMeter = squareMeter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SquareMeter getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(SquareMeter squareMeter) {
        this.squareMeter = squareMeter;
    }
}
