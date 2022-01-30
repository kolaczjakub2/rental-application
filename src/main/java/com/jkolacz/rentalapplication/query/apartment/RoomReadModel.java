package com.jkolacz.rentalapplication.query.apartment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APARTMENT_ROOM")
public class RoomReadModel {
    @Id
    private String id;
    private String name;
    private Double size;

    public RoomReadModel(String id, String name, Double size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }
}
