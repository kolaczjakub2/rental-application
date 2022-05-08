package com.jkolacz.rentalapplication.query.apartment;

import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateField")
public class RoomReadModel {
    private String name;
    private Double size;

    private RoomReadModel() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
