package com.jkolacz.rentalapplication.domain.hotelRoom;

import javax.persistence.Embeddable;

@Embeddable
class SquareMeter {
    private Double value;

    SquareMeter(Double value) {
        this.value = value;
    }
}
