package com.jkolacz.rentalapplication.domain.hotelRoom;

import javax.persistence.Embeddable;

@Embeddable
class SquareMeter {
    private Double value;

    SquareMeter() {
    }

    SquareMeter(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
