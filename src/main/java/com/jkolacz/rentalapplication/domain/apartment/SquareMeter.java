package com.jkolacz.rentalapplication.domain.apartment;

import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
class SquareMeter {
    private Double size;

    private SquareMeter() {
    }

    SquareMeter(Double size) {
        this.size = size;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
