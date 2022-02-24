package com.jkolacz.rentalapplication.domain.hotel;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "HOTEL")
public class Hotel {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @Embedded
    private Address address;

    public Hotel() {
    }

    Hotel(String name, Address address) {

        this.name = name;
        this.address = address;
    }
}
