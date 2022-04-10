package com.jkolacz.rentalapplication.query.apartment;

import javax.persistence.Embeddable;

@Embeddable
public class RoomReadModel {
    private String name;
    private Double size;

    private RoomReadModel() {}

}
