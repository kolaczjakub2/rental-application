package com.jkolacz.rentalapplication.domain.hotel;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "HOTEL")
public class Hotel {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
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
}
