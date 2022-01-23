package com.jkolacz.rentalapplication.domain.hotelRoom;

import javax.persistence.Embeddable;

@Embeddable
class SquareMeter {
    private Double size;

    SquareMeter(Double size) {
        this.size = size;
    }
}
