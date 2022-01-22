package com.jkolacz.rentalapplication.domain.apartment;

import javax.persistence.Embeddable;

@Embeddable
class SquareMeter {
    private Double size;

    SquareMeter(Double size) {
        this.size = size;
    }
}
