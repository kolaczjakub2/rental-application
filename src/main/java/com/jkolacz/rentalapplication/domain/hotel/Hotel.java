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

    private Hotel() {}

    Hotel(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
