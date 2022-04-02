package com.jkolacz.rentalapplication.domain.hotelRoom;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public
class Space {
    private  String name;
    @Embedded
    private SquareMeter squareMeter;

    public Space() {
    }

    Space(String name, SquareMeter squareMeter) {
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
