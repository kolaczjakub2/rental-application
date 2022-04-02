package com.jkolacz.rentalapplication.query.hotelRoom;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SpaceReadModel {

    @Id
    private long id;

    private String name;
    private Double value;

    private SpaceReadModel() {
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    public long getId() {
        return id;
    }
}