package com.jkolacz.rentalapplication.query.apartment;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "APARTMENT")
public class ApartmentReadModel {

    @Id
    @GeneratedValue
    private UUID id;
    private String ownerId;

    private String street;
    private String postalCode;
    private String houseNumber;
    private String apartmentNumber;
    private String city;
    private String country;
    private String description;
    @ElementCollection
    private List<RoomReadModel> rooms;

    public ApartmentReadModel() {
    }

    public ApartmentReadModel(UUID id, String ownerId, String street, String postalCode, String houseNumber, String apartmentNumber,
                              String city, String country, String description, List<RoomReadModel> rooms) {
        this.id = id;
        this.ownerId = ownerId;
        this.street = street;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.country = country;
        this.description = description;
        this.rooms = rooms;
    }

    public UUID getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public List<RoomReadModel> getRooms() {
        return rooms;
    }
}
